package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dto.ReviewBoard;
import dto.ReviewDetailView;
import dto.ReviewImgFile;
import dto.ReviewUserJoin;
import util.Paging;

public class ReviewDaoImpl implements ReviewDao {
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int selectCntAll(Connection conn) {
		
		String sql = "";
		sql += "SELECT COUNT(*) cnt FROM review_board";
		
//		sql +=" SELECT count(*) FROM (";
//		sql +="	    SELECT rownum rnum, R.* FROM (";
//		sql +="	            SELECT";
//		sql +="	                review_no, s.review_sort_detail, title, u.user_id, create_date, views";
//		sql +="	            FROM review_board b, review_board_sort s, usertb u";
//		sql +="	            WHERE b.review_sort = s.review_sort AND b.user_no = u.user_no";
//		sql +="	            ORDER BY review_no DESC";
//		sql +="	        ) R";
//		sql +="	    ) REVIEW";
		
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
	} //selectCntAll(Connection conn) end
	
	
	@Override
	public List<ReviewUserJoin> selectAll(Connection conn, Paging paging) {

		String sql = "";
		sql += " SELECT * FROM (";
		sql += "	    SELECT rownum rnum, R.* FROM (";
		sql += "	           SELECT";
		sql += "	               review_no, s.review_sort_detail, title, u.user_id, create_date, views";
		sql += "	           FROM review_board b, review_board_sort s, usertb u";
		sql += "	           WHERE b.review_sort = s.review_sort AND b.user_no = u.user_no";
		sql += "	           ORDER BY review_no DESC";
		sql += "	       ) R";
		sql += "	   ) REVIEW";
		sql += "	   WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장
		List<ReviewUserJoin> reviewBoardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReviewUserJoin review = new ReviewUserJoin(); // 결과값 저장 객체
				
				review.setReviewNo(rs.getInt("review_no"));
				review.setReviewSortDetail(rs.getString("review_sort_detail"));
				review.setTitle(rs.getString("title"));
				review.setUserId(rs.getString("user_id"));
				review.setCreateDate(rs.getDate("create_date"));
				review.setViews(rs.getInt("views"));
				
				//리스트에 저장
				reviewBoardList.add(review);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return reviewBoardList;
	}

	@Override
	public int selectReviewNo(Connection conn) {

		String sql = "";
		sql += "SELECT review_seq.nextval FROM dual";
		
		//결과 저장 변수
		int reviewNo = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				reviewNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return reviewNo;
	}
	
	@Override
	public int insert(Connection conn, ReviewBoard reviewBoard) {
		
		String sql = "";
		sql += "INSERT INTO review_board(review_no, review_sort, user_no, title, content, views)";
		sql += " VALUES (?, ?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewBoard.getReviewNo());
			ps.setInt(2, reviewBoard.getReviewSort());
			ps.setInt(3, reviewBoard.getUserNo());
			ps.setString(4, reviewBoard.getTitle());
			ps.setString(5, reviewBoard.getContent());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
	
	@Override
	public int insertImgFile(Connection conn, List<ReviewImgFile> reviewImgs) {

		String sql = "";
		sql += "INSERT INTO review_img(image_no, review_no, origin_img, stored_img)";
		sql += " VALUES (review_img_seq.nextval, ?, ?, ?)";
		
		int res = 0;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			for(int i=0; i<reviewImgs.size(); i++) {
				ps.setInt(1, reviewImgs.get(i).getReviewNo());
				ps.setString(2, reviewImgs.get(i).getOriginImg());
				ps.setString(3, reviewImgs.get(i).getStoredImg());
				result += ps.executeUpdate();
			}
			res = result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public ReviewDetailView selectReviewBoardByReviewNo(Connection conn, ReviewBoard reviewNo) {

		String sql = "";
//		sql += "SELECT * FROM review_board";
//		sql += " WHERE review_no = ?";
		
		sql +=" SELECT";
		sql +="   s.review_sort_detail, title, u.nick, create_date, content";
		sql +=" FROM review_board b, review_board_sort s, usertb u";
		sql +=" WHERE b.review_sort = s.review_sort AND b.user_no = u.user_no AND review_no = ?";
		
		//결과 저장할 객체
		ReviewDetailView reviewDetailView = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewNo.getReviewNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				reviewDetailView = new ReviewDetailView();
				
//				reviewDetailView.setReviewNo(rs.getInt("review_no"));
				reviewDetailView.setReviewSortDetail(rs.getString("review_sort_detail"));
				reviewDetailView.setTitle(rs.getString("title"));
				reviewDetailView.setNick(rs.getString("nick"));
				reviewDetailView.setCreateDate(rs.getDate("create_date"));
				reviewDetailView.setContent(rs.getString("content"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return reviewDetailView;
	}
	
	@Override
	public int updateViews(Connection conn, ReviewBoard reviewNo) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "UPDATE review_board";
		sql += " SET views = views + 1";
		sql += " WHERE review_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewNo.getReviewNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
	
	
	@Override
	public List<ReviewImgFile> selectReviewImgs(Connection conn, ReviewBoard reviewNo) {

		String sql = "";
		sql += "SELECT * FROM review_img";
		sql += " WHERE review_no = ?";
		sql += " ORDER BY image_no DESC";
		
		//결과 저장 List 객체
		List<ReviewImgFile> reviewImgs = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewNo.getReviewNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReviewImgFile imgFile = new ReviewImgFile();
				
				imgFile.setImageNo(rs.getInt("image_no"));
				imgFile.setReviewNo(rs.getInt("review_no"));
				imgFile.setOriginImg(rs.getString("origin_img"));
				imgFile.setStoredImg(rs.getString("stored_img"));
				
				reviewImgs.add(imgFile);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return reviewImgs;
	}
	
	
	@Override
	public int deleteImgFile(Connection conn, ReviewBoard reviewBoard) {

		String sql = "";
		sql += "DELETE FROM review_img";
		sql += " WHERE review_no = ?";
		
		PreparedStatement ps = null;
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewBoard.getReviewNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	
	@Override
	public int update(Connection conn, ReviewBoard reviewBoard) {
		
		String sql = "";
		sql += "UPDATE review_board";
		sql += " SET title = ?,";
		sql += "	content = ?,";
		sql += "	update_date = sysdate";
		sql += " WHERE review_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, reviewBoard.getTitle());
			ps.setString(2, reviewBoard.getContent());
			ps.setInt(3, reviewBoard.getReviewNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	
	@Override
	public int insertImg(Connection conn, List<ReviewImgFile> reviewImgs) {
		
		String sql = "";
		sql += "INSERT INTO review_img";
		sql += " VALUES (review_img_seq.nextval, ?, ?, ?)";
		
		int result = -1;
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			for(int i=0; i < reviewImgs.size(); i++) {
				ps.setInt(1, reviewImgs.get(i).getReviewNo());
				ps.setString(2, reviewImgs.get(i).getOriginImg());
				ps.setString(3, reviewImgs.get(i).getStoredImg());
				
				res += ps.executeUpdate();
			}
			result = res;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	
	@Override
	public int deleteFile(Connection conn, ReviewBoard reviewNo) {
		
		String sql = "";
		sql += "DELETE FROM review_img";
		sql += " WHERE review_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewNo.getReviewNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	
	@Override
	public int delete(Connection conn, ReviewBoard reviewNo) {
		
		String sql = "";
		sql += "DELETE FROM review_board";
		sql += " WHERE review_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewNo.getReviewNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
}
