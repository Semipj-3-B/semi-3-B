package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.LoginDao;
import dto.Usertb;

public class LoginDaoImpl implements LoginDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntByUserId(Connection conn, Usertb param) {
		
		//로그인
		//입력한 id와 pw가 맞는지 조회
		String sql = "";
		sql += "SELECT count(*) FROM usertb";
		sql += " WHERE 1=1";
		sql += " AND user_id = ?";
		sql += " AND user_pw = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, param.getUserId());
			ps.setString(2, param.getUserPw());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public Usertb selectUser(Connection conn, Usertb param) {
		
		//id값으로 유저정보 조회
		String sql = "";
		sql += "SELECT * FROM usertb";
		sql += " WHERE 1=1";
		sql += " AND user_id = ?";
		
		Usertb user = null;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, param.getUserId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new Usertb();
				
				user.setUserId(rs.getString("user_id"));
				user.setNick(rs.getString("nick"));
				user.setUserNo(rs.getInt("user_no"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return user;
	}

}
