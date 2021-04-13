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

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int updateHit(Connection conn, FindBoard findNo) {
		 
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM findboard";
		sql += " SET views = views + 1";
		sql += " WHERE findno = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, findNo.getFindNo()); // 조회할 게시글 번호 적용

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return res;
	}
	

	@Override
	public FindBoard selectFind(Connection conn, FindBoard findNo) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM findboard";
		sql += " WHERE findno = ?";
		
		//결과 저장할 Board객체
		FindBoard viewFindBoard = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, findNo.getFindNo()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewFindBoard = new FindBoard(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewFindBoard.setTitle( rs.getString("title") );
				viewFindBoard.setCreateDate( rs.getDate("create_Date") );
				viewFindBoard.setPetName( rs.getString("petName") );
				viewFindBoard.setPetKinds( rs.getString("petKinds") );
				viewFindBoard.setPetAge( rs.getInt("petAge") );
				viewFindBoard.setLoc( rs.getString("loc") );
				viewFindBoard.setContent( rs.getString("content") );
								
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return viewFindBoard;
	}


	@Override
	public String selectNickByUserNo(Connection connection, FindBoard viewFindBoard) {
				
		//SQL
		String sql = "";
		sql += "SELECT nick FROM usertb";
		sql += " WHERE userno = ?";
		
		//결과
		String nick = null;
		
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, viewFindBoard.getUserNo()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				nick = rs.getString("nick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return nick;
	}


	@Override
	public FindImg selectFile(Connection connection, FindBoard viewFindBoard) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM findimg";
		sql += " WHERE findno = ?";
		
		//결과 저장할 BoardFile 객체
		FindImg findImg = null;
		
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewFindBoard.getFindNo()); //조회할 boardno 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				findImg = new FindImg();
				
				findImg.setImgNum( rs.getInt("imgNum") );
				findImg.setFindNo( rs.getInt("findNo") );
				findImg.setOriginImg( rs.getString("originImg") );
				findImg.setStoredImg( rs.getString("sotredImg") );
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		
		return findImg;
	}
	
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
