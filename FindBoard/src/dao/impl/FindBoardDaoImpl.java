package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.FindBoardDao;
import dto.FindBoard;
import dto.FindImg;

public class FindBoardDaoImpl implements FindBoardDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectFindno(Connection conn) {
		String sql = "";
		sql += "SELECT findboard_seq.nextval FROM dual";
		
		int findno = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				findno = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return findno;
	}

	@Override
	public int insert(Connection conn, FindBoard findBoard) {
		String sql = "";
		sql += "INSERT INTO findboard (find_no, user_no, title, views"
									+ ", pet_name, pet_kinds, pet_age"
									+ ", loc, content, board_div)";
		sql += " VALUES (?, ?, ?, 0"
				+ ", ?, ?, ?"
				+ ", ?, ?, 1)";
		
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, findBoard.getFindNo());
			ps.setInt(2, findBoard.getUserNo());
			ps.setString(3, findBoard.getTitle());
			ps.setString(4, findBoard.getPetName());
			ps.setString(5, findBoard.getPetKinds());
			ps.setInt(6, findBoard.getPetAge());
			ps.setString(7, findBoard.getLoc());
			ps.setString(8, findBoard.getContent());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	@Override
	public int insertImg(Connection conn, FindImg findImg) {
		String sql = "";
		sql = "INSERT INTO findimg (image_no, find_no, origin_img, stored_img)";
		sql = " VALUES (findimg_seq.nextval, ?, ?, ?)";
		
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, findImg.getFindNo());
			ps.setString(2, findImg.getOriginImg());
			ps.setString(3, findImg.getStoredImg());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

}
