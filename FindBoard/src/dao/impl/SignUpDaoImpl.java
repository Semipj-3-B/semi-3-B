package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.SignUpDao;
import dto.Usertb;

public class SignUpDaoImpl implements SignUpDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int insertUser(Connection conn, Usertb user) {
		
		//회원가입 정보 저장
		String sql = "";
		sql += "INSERT INTO usertb(user_no, user_id, user_pw, nick, year, month, day, name, gender, email, phone, postnum, addr, addr_detail)";
		sql += " VALUES(usertb_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserPw());
			ps.setString(3, user.getNick());
			ps.setInt(4, user.getYear());
			ps.setInt(5, user.getMonth());
			ps.setInt(6, user.getDay());
			ps.setString(7, user.getName());
			ps.setString(8, user.getGender());
			ps.setString(9, user.getEmail());
			ps.setString(10, user.getPhone());
			ps.setInt(11, user.getPostnum());
			ps.setString(12, user.getAddr());
			ps.setString(13, user.getAddrDetail());
			
			res = ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	} // insertUser() end
	
	@Override
	public int selectId(Connection conn, String id) {
		
		//아이디 중복 조회
		String sql = "";
		sql += "SELECT * FROM usertb";
		sql += " WHERE user_id = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, id);
		
			rs = ps.executeQuery();
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return res;
	}
	
	@Override
	public int selectNick(Connection conn, String nick) {
		
		//닉네임 중복 조회
		String sql = "";
		sql += "SELECT * FROM usertb";
		sql += " WHERE nick = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, nick);
		
			rs = ps.executeQuery();
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return res;
	}
	
	
	
}
