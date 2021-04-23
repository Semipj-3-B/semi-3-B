package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ReviewCommentDao;
import dto.ReviewComment;

public class ReviewCommentDaoImpl implements ReviewCommentDao {
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체

	@Override
	public int insertComment(Connection conn, ReviewComment param) {

		String sql = "";
		sql += "INSERT INTO review_comment (comment_no, review_no, user_no, nick, comment_text)";
		sql += " VALUES (review_comment_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, param.getReviewNo());
			ps.setInt(2, param.getUserNo());
			ps.setString(3, param.getNick());
			ps.setString(4, param.getCommentText());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	
	@Override
	public List<ReviewComment> selectComment(Connection conn, int reviewNo) {

		String sql = "";
		sql += "select comment_no, review_no, R.user_no, nick, comment_text, comment_date, U.storedname,(";
		sql += "    select count(*) from review_comment where review_no = ?) count";
		sql += " from review_comment R, userimg U";
		sql += " where R.user_no = U.user_no and review_no = ?";
		sql += " order by comment_no desc";
		
		List<ReviewComment> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewNo);
			ps.setInt(2, reviewNo);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReviewComment rc = new ReviewComment();
				
				rc.setCommentNo(rs.getInt("comment_no"));
				rc.setReviewNo(rs.getInt("review_no"));
				rc.setUserNo(rs.getInt("user_no"));
				rc.setNick(rs.getString("nick"));
				rc.setCommentText(rs.getString("comment_text"));
				rc.setCommentDate(rs.getDate("comment_date"));
				rc.setCommentCnt(rs.getInt("count"));
				rc.setStoredName(rs.getString("storedname"));
				
				list.add(rc);
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
	public int deleteComment(Connection conn, ReviewComment param) {
		
		String sql = "";
		sql += "DELETE FROM review_comment";
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
