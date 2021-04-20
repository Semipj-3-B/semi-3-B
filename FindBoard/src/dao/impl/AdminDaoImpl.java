package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminDao;
import dto.Usertb;
import util.AdminPaging;

public class AdminDaoImpl implements AdminDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Usertb> selectAll(Connection conn) {
		String sql = "";
		sql += "SELECT user_no, user_id, nick, email FROM usertb";
		sql += " ORDER BY user_no";
		
		List<Usertb> userList = new ArrayList<Usertb>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Usertb user = new Usertb();
				user.setUserNo(rs.getInt("user_no"));
				user.setUserId(rs.getString("user_id"));
				user.setNick(rs.getString("nick"));
				user.setEmail(rs.getString("email"));
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userList;
	}

	@Override
	public int selectCntAll(Connection conn) {
		String sql = "";
		sql += "SELECT count(*) cnt FROM board";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cnt;
	}

	@Override
	public List<Usertb> selectAll(Connection conn, AdminPaging apaging) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " 	SELECT rownum rnum, U.* FROM (";
		sql += " 		SELECT user_no, user_id, nick, email FROM usertb";
		sql += " 		ORDER BY user_no ";
		sql += "	) U";
		sql += " ) ";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Usertb> userList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, apaging.getStartNo());
			ps.setInt(2, apaging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Usertb user = new Usertb(); 
				
				user.setUserNo(rs.getInt("user_no"));
				user.setUserId(rs.getString("user_id"));
				user.setNick(rs.getString("nick"));
				user.setEmail(rs.getString("email"));
				
				userList.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userList;
	}
}
