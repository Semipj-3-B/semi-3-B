package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.JDBCTemplate;
import dao.face.DiscoverBoardDao;
import dto.DiscoverBoard;
import dto.DiscoverComment;
import dto.DiscoverImg;
import dto.FindComment;
import util.Paging;

public class DiscoverBoardDaoImpl implements DiscoverBoardDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	@Override
	public List<DiscoverBoard> selectAll(Connection conn, Paging paging, Map<String, String> map) {
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " 	SELECT rownum rnum, F.* FROM (";
		sql += " 		SELECT";
		sql += " 			discover_no, user_no, title, create_date, update_date, views";
		sql += " 			, pet_name, pet_kinds, pet_age, loc, content, board_div";
		sql += " 		FROM discoverboard";
		if (map.get("pet") != null && map.get("loc") != null) {
			sql += " WHERE loc=? AND pet_kinds=?";
		}
		sql += " 		ORDER BY discover_no DESC";
		sql += "	) F";
		sql += " ) discoverboard";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<DiscoverBoard> discoverboardList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			if (map.get("pet") == null && map.get("loc") == null) {
				ps.setInt(1, paging.getStartNo());
				ps.setInt(2, paging.getEndNo());
			} else {
				ps.setString(1, map.get("loc"));
				ps.setString(2, map.get("pet"));
				ps.setInt(3, paging.getStartNo());
				ps.setInt(4, paging.getEndNo());
			}
			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				DiscoverBoard f = new DiscoverBoard(); // 결과값 저장 객체

				// 결과값 한 행 처리
				f.setDiscoverNo(rs.getInt("discover_No"));
				f.setUserNo(rs.getInt("user_No"));
				f.setTitle(rs.getString("title"));
				f.setCreateDate(rs.getDate("create_Date"));
				f.setUpdateDate(rs.getDate("update_Date"));
				f.setViews(rs.getInt("views"));
				f.setPetName(rs.getString("pet_Name"));
				f.setPetKinds(rs.getString("pet_Kinds"));
				f.setPetAge(rs.getInt("pet_Age"));
				f.setLoc(rs.getString("loc"));
				f.setContent(rs.getString("content"));
				f.setBoardDiv(rs.getInt("board_Div"));

				// 리스트에 결과값 저장
				discoverboardList.add(f);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return discoverboardList;
	}
	@Override
	public int selectCntAll(Connection conn) {
		
		String sql = "";
		sql += "SELECT count(*) cnt FROM discoverboard";
		
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
	public DiscoverBoard selectBoardByDiscoverno(Connection conn, DiscoverBoard discoverno) {
		
		String sql = "";
		sql += "SELECT * FROM discoverboard";
		sql += " WHERE discover_no = ?";
		
		//결과 저장할 Board객체
		DiscoverBoard viewdiscoverboard = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, discoverno.getDiscoverNo() ); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewdiscoverboard = new DiscoverBoard(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewdiscoverboard.setTitle( rs.getString("title") );
				viewdiscoverboard.setCreateDate( rs.getDate("create_Date") );
				viewdiscoverboard.setPetName( rs.getString("pet_Name") );
				viewdiscoverboard.setPetKinds( rs.getString("pet_Kinds") );
				viewdiscoverboard.setPetAge( rs.getInt("pet_Age") );
				viewdiscoverboard.setLoc( rs.getString("loc") );
				viewdiscoverboard.setContent( rs.getString("content") );
				viewdiscoverboard.setDiscoverNo( rs.getInt("discover_No") );
				viewdiscoverboard.setUserNo( rs.getInt("user_No") );
				viewdiscoverboard.setUpdateDate( rs.getDate("update_Date") );
								
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return viewdiscoverboard;
	}
	@Override
	public int updateHit(Connection conn, DiscoverBoard discoverno) {
		 
		//SQL 작성
		String sql = "";
		sql += "UPDATE discoverboard";
		sql += " SET views = views + 1";
		sql += " WHERE discover_no = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, discoverno.getDiscoverNo()); // 조회할 게시글 번호 적용

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
	@Override
	public String selectNickByUserNo(Connection conn, DiscoverBoard viewDiscoverBoard) {
		
		//SQL
		String sql = "";
		sql += "SELECT nick FROM usertb";
		sql += " WHERE user_no = ?";
		
		//결과
		String nick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, viewDiscoverBoard.getUserNo()); //조회할 id 적용
			
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
	public String selectEmailByUserNo(Connection conn, DiscoverBoard viewDiscoverBoard) {
		//SQL
		String sql = "";
		sql += "SELECT email FROM usertb";
		sql += " WHERE user_no = ?";
			
		//결과
		String email = null;
			
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, viewDiscoverBoard.getUserNo()); //조회할 id 적용
				
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
	public List<DiscoverImg> selectFile(Connection conn, DiscoverBoard viewDiscoverBoard) {

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM discoverimg";
		sql += " WHERE discover_no = ?";
		sql += " ORDER BY image_no DESC";
		
		//결과 저장할 BoardFile 객체
		List<DiscoverImg> dicoverImg = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewDiscoverBoard.getDiscoverNo()); //조회할 boardno 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				DiscoverImg di = new DiscoverImg();
				
				di.setDiscoverNo( rs.getInt("discover_No") );
				di.setImgNum( rs.getInt("image_No") );
				di.setOriginImg( rs.getString("origin_Img") );
				di.setStoredImg( rs.getString("stored_Img") );
				
				//discoverimg 리스트에 결과값 저장
				dicoverImg.add(di);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		
		return dicoverImg;
	}
	@Override
	public int selectDiscoverno(Connection conn) {
		String sql = "";
		sql += "SELECT discoverboard_seq.nextval FROM dual";
		
		int discoverno = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				discoverno = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return discoverno;
	}
	@Override
	public int insert(Connection conn, DiscoverBoard discoverBoard) {
		String sql = "";
		sql += "INSERT INTO discoverboard (discover_no, user_no, title, views"
									+ ", pet_name, pet_kinds, pet_age"
									+ ", loc, content, board_div)";
		sql += " VALUES (?, ?, ?, 0"
				+ ", ?, ?, ?"
				+ ", ?, ?, 1)";
		
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, discoverBoard.getDiscoverNo());
			ps.setInt(2, discoverBoard.getUserNo());
			ps.setString(3, discoverBoard.getTitle());
			ps.setString(4, discoverBoard.getPetName());
			ps.setString(5, discoverBoard.getPetKinds());
			ps.setInt(6, discoverBoard.getPetAge());
			ps.setString(7, discoverBoard.getLoc());
			ps.setString(8, discoverBoard.getContent());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	@Override
	public int insertImg(Connection conn, List<DiscoverImg> discoverImages) {
		String sql = "";
		sql += "INSERT INTO discoverimg (image_no, discover_no, origin_img, stored_img)";
		sql += " VALUES (discoverimg_seq.nextval, ?, ?, ?)";
		
		int result = -1;
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < discoverImages.size(); i++) {
				ps.setInt(1, discoverImages.get(i).getDiscoverNo());
				ps.setString(2, discoverImages.get(i).getOriginImg());
				ps.setString(3, discoverImages.get(i).getStoredImg());
			
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
	@Override
	public int deleteFile(Connection conn, DiscoverBoard discoverboard) {
		String sql = "";
		sql += "DELETE discoverimg";
		sql += " WHERE find_no = ?";
				

		PreparedStatement ps = null; 
				
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, discoverboard.getDiscoverNo());

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
	public int delete(Connection conn, DiscoverBoard discoverboard) {
		String sql = "";
		sql += "DELETE discoverboard";
		sql += " WHERE discover_no = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
				
		int res = -1;
			
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, discoverboard.getDiscoverNo());
			
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
	public int update(Connection conn, DiscoverBoard discoverBoard) {
//System.out.println("discoverboard.getdiscoverno() = " + discoverboard.getdiscoverno());
		
		String sql = "";
		sql += "UPDATE discoverboard";
		sql += " SET title = ?,";
		sql += " 	content = ?,";
		sql += "	update_date = sysdate";
		sql += " WHERE discover_no = ?";
		
		System.out.println("DAO - update 도착");
		
		//DB 객체
		PreparedStatement ps = null; 
				
		int res = -1;		
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, discoverBoard.getTitle());
			ps.setString(2, discoverBoard.getContent());
			ps.setInt(3, discoverBoard.getDiscoverNo());

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
		System.out.println("DAO - res = " + res);
		return res;
	}
	@Override
	public List<DiscoverComment> selectComment(Connection conn, int discoverNo) {

		String sql = "";
		sql += "SELECT * FROM discoverboard_comment";
		sql += " WHERE discover_no = ?";
		sql += " ORDER BY comment_no";
		
		
		List<DiscoverComment> list = new ArrayList<>();
		
		try {	
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, discoverNo);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				DiscoverComment res = new DiscoverComment();
				
				res.setNick( rs.getString("nick") );
				res.setCommentNo( rs.getInt("comment_no") );
				res.setCommentText( rs.getString("comment_text") );
				res.setCommentDate( rs.getDate("comment_date") );
				res.setCommentUpdate( rs.getDate("comment_update") );

				list.add(res);				
				
			}			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
			
		}
		
		return list;
	}
	@Override
	public int insertComment(Connection conn, DiscoverComment param) {

		String sql = "";
		sql += "INSERT INTO discoverboard_comment( comment_no, discover_no, user_no, nick, comment_text)";
		sql += " VALUES (discoverboard_comment_seq.nextval, ?, ?, ?, ?)";
		
		System.out.println("DAO - inserComment 전달 완료 ");
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, param.getDiscoverNo() );
			ps.setInt(2, param.getUserNo() );
			ps.setString(3, param.getNick() );
			ps.setString(4, param.getCommentText() );
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	@Override
	public int updateComment(Connection conn, DiscoverComment param) {

		String sql = "";
		sql += "UPDATE discoverboard_comment";
		sql += " SET comment_text = ?,";
		sql += "	comment_update = sysdate";
		sql += " WHERE comment_no = ?";
		
		
		int res = 0;
		
		try {
			ps=conn.prepareStatement(sql);

			ps.setString(1, param.getCommentText());
			ps.setInt(2, param.getCommentNo());
			
			res = ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	@Override
	public int deleteComment(Connection conn, DiscoverComment param) {
		String sql = "";
		sql += "DELETE discoverboard_comment";
		sql += " WHERE comment_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, param.getCommentNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	

}
