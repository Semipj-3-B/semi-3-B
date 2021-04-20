package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.face.FindBoardDao;
import dto.FindBoard;
import dto.FindImg;
import oracle.net.aso.f;
import util.Paging;
import common.JDBCTemplate;

public class FindBoardDaoImpl implements FindBoardDao {
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<FindBoard> selectAll(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT find_no, user_no, title, create_date, update_date, views, pet_name, pet_kinds, pet_age, loc, content, board_div FROM findboard";
		sql += " ORDER BY find_no DESC";
		
		//결과 저장할 List
		List<FindBoard> findboardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				FindBoard f = new FindBoard(); //결과값 저장 객체
				
				//결과값 한 행 처리
				f.setFindNo( rs.getInt("find_No") );
				f.setUserNo( rs.getInt("user_No") );
				f.setTitle( rs.getString("title") );
				f.setCreateDate( rs.getDate("create_Date") );
				f.setUpdateDate( rs.getDate("update_Date") );
				f.setViews( rs.getInt("views") );
				f.setPetName( rs.getString("pet_Name") );
				f.setPetKinds( rs.getString("pet_Kinds") );
				f.setPetAge( rs.getInt("pet_Age") );
				f.setLoc( rs.getString("loc") );
				f.setContent( rs.getString("content"));
				f.setBoardDiv( rs.getInt("board_Div") );
				
				//리스트에 결과값 저장
				findboardList.add(f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return findboardList;
	}

	@Override
	public int selectCntAll(Connection conn) {
		String sql = "";
		sql += "SELECT count(*) cnt FROM findboard";
		
		//총 게시글 수
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
	public List<FindBoard> selectAll(Connection conn, Paging paging) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " 	SELECT rownum rnum, F.* FROM (";
		sql += " 		SELECT";
		sql += " 			find_no, user_no, title, create_date, update_date, views";
		sql += " 			, pet_name, pet_kinds, pet_age, loc, content, board_div";
		sql += " 		FROM findboard";
		sql += " 		ORDER BY find_no DESC";
		sql += "	) F";
		sql += " ) findboard";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<FindBoard> findboardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				FindBoard f = new FindBoard(); //결과값 저장 객체
				
				//결과값 한 행 처리
				f.setFindNo( rs.getInt("find_No") );
				f.setUserNo( rs.getInt("user_No") );
				f.setTitle( rs.getString("title") );
				f.setCreateDate( rs.getDate("create_Date") );
				f.setUpdateDate( rs.getDate("update_Date") );
				f.setViews( rs.getInt("views") );
				f.setPetName( rs.getString("pet_Name") );
				f.setPetKinds( rs.getString("pet_Kinds") );
				f.setPetAge( rs.getInt("pet_Age") );
				f.setLoc( rs.getString("loc") );
				f.setContent( rs.getString("content"));
				f.setBoardDiv( rs.getInt("board_Div") );
				
				//리스트에 결과값 저장
				findboardList.add(f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return findboardList;
	}

	@Override
	public FindBoard selectBoardByFindno(Connection conn, FindBoard find_no) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM findboard";
		sql += " WHERE find_no = ?";
		
		//결과 저장할 Board객체
		FindBoard viewFindBoard = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, find_no.getFindNo()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewFindBoard = new FindBoard(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewFindBoard.setTitle( rs.getString("title") );
				viewFindBoard.setCreateDate( rs.getDate("create_Date") );
				viewFindBoard.setPetName( rs.getString("pet_Name") );
				viewFindBoard.setPetKinds( rs.getString("pet_Kinds") );
				viewFindBoard.setPetAge( rs.getInt("pet_Age") );
				viewFindBoard.setLoc( rs.getString("loc") );
				viewFindBoard.setContent( rs.getString("content") );
				viewFindBoard.setFindNo( rs.getInt("find_No") );
				viewFindBoard.setUserNo( rs.getInt("user_No") );
				viewFindBoard.setUpdateDate( rs.getDate("update_Date") );
								
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
	public int updateHit(Connection conn, FindBoard findNo) {
		 
		//SQL 작성
		String sql = "";
		sql += "UPDATE findboard";
		sql += " SET views = views + 1";
		sql += " WHERE find_no = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, findNo.getFindNo()); // 조회할 게시글 번호 적용

//			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return res;
	}
	

//	@Override
//	public FindBoard selectFind(Connection conn, FindBoard findNo) {
//		//SQL 작성
//		String sql = "";
//		sql += "SELECT * FROM findboard";
//		sql += " WHERE find_no = ?";
//		
//		//결과 저장할 Board객체
//		FindBoard viewFindBoard = null;
//		
//		try {
//			ps = conn.prepareStatement(sql); //SQL수행 객체
//			
//			ps.setInt(1, findNo.getFindNo()); //조회할 게시글 번호 적용
//			
//			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
//			
//			//조회 결과 처리
//			while(rs.next()) {
//				viewFindBoard = new FindBoard(); //결과값 저장 객체
//				
//				//결과값 한 행 처리
//				viewFindBoard.setTitle( rs.getString("title") );
//				viewFindBoard.setCreateDate( rs.getDate("create_Date") );
//				viewFindBoard.setPetName( rs.getString("pet_Name") );
//				viewFindBoard.setPetKinds( rs.getString("pet_Kinds") );
//				viewFindBoard.setPetAge( rs.getInt("pet_Age") );
//				viewFindBoard.setLoc( rs.getString("loc") );
//				viewFindBoard.setContent( rs.getString("content") );
//								
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			//DB객체 닫기
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(ps);
//		}
//		
//		//최종 결과 반환
//		return viewFindBoard;
//	}


	@Override
	public String selectNickByUserNo(Connection conn, FindBoard viewFindBoard) {
				
		//SQL
		String sql = "";
		sql += "SELECT nick FROM usertb";
		sql += " WHERE user_no = ?";
		
		//결과
		String nick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
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
	public String selectEmailByUserNo(Connection conn, FindBoard viewFindBoard) {
			//SQL
			String sql = "";
			sql += "SELECT email FROM usertb";
			sql += " WHERE user_no = ?";
				
			//결과
			String email = null;
				
			try {
				ps = conn.prepareStatement(sql); //SQL수행 객체
				ps.setInt(1, viewFindBoard.getUserNo()); //조회할 id 적용
					
				rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
					
				//조회 결과 처리
				while(rs.next()) {
					email = rs.getString("email");
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//DB객체 닫기
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
				
			//최종 결과 반환
			return email;
	}


	@Override
	public FindImg selectFile(Connection connection, FindBoard viewFindBoard) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM findimg";
		sql += " WHERE find_no = ?";
		
		//결과 저장할 BoardFile 객체
		FindImg findImg = null;
		
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewFindBoard.getFindNo()); //조회할 boardno 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				findImg = new FindImg();
				
				findImg.setFindNo( rs.getInt("find_No") );
				findImg.setImgNum( rs.getInt("image_No") );
				findImg.setOriginImg( rs.getString("origin_Img") );
				findImg.setStoredImg( rs.getString("stored_Img") );
				
				
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
	public int insertImg(Connection conn, List<FindImg> findImages) {
		for(FindImg f : findImages){
			System.out.println(f);
		}
		
		String sql = "";
		sql += "INSERT INTO findimg (image_no, find_no, origin_img, stored_img)";
		sql += " VALUES (findimg_seq.nextval, ?, ?, ?)";
		
		int result = -1;
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < findImages.size(); i++) {
				ps.setInt(1, findImages.get(i).getFindNo());
				ps.setString(2, findImages.get(i).getOriginImg());
				ps.setString(3, findImages.get(i).getStoredImg());
			
				res += ps.executeUpdate();
			}//for() END
			
			result = res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	@Override
	public int selectUserno(Connection conn, String userid) {
		String sql = "";
		sql += "SELECT user_no FROM usertb";
		sql += " WHERE user_id = ? ";
		
		int userno = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				userno = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userno;
	}

//	@Override
//	public int deleteFile(Connection conn, FindBoard findboard) {
//		
//		String sql = "";
//		sql += "DELETE findboardfile";
//		sql += " WHERE find_no = ?";
//				
//
//		PreparedStatement ps = null; 
//				
//		int res = -1;
//		
//		try {
//			//DB작업
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, findboard.getFindNo());
//
//			res = ps.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			
//		} finally {
//			try {
//				//DB객체 닫기
//				if(ps!=null)	ps.close();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return res;
//		
//	}

	@Override
	public int delete(Connection conn, FindBoard findboard) {
		
		
		String sql = "";
		sql += "DELETE findboard";
		sql += " WHERE find_no = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
				
		int res = -1;
			
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, findboard.getFindNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

	@Override
	public int update(Connection conn, FindBoard findboard) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE findboard";
		sql += " SET title = ?,";
		sql += " 	content = ?,";
		sql += "	update_date = sysdate,";
		sql += " 	find_no = findboard_seq.nextval";
		sql += " WHERE find_no = ?";
		
		
		//DB 객체
		PreparedStatement ps = null; 
				
		int res = -1;		
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, findboard.getTitle());
			ps.setString(2, findboard.getContent());
			ps.setInt(3, findboard.getFindNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
		
	}


}
