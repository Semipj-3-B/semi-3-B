package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.ReviewComment;

public interface ReviewCommentDao {

	/**
	 * 댓글 insert
	 * 
	 * @param conn
	 * @param param
	 * @return
	 */
	public int insertComment(Connection conn, ReviewComment param);

	/**
	 * 댓글 목록 조회
	 * 
	 * @param conn
	 * @param reviewNo
	 * @return
	 */
	public List<ReviewComment> selectComment(Connection conn, int reviewNo);

	/**
	 * 댓글삭제
	 * 
	 * @param conn
	 * @param param
	 * @return
	 */
	public int deleteComment(Connection conn, ReviewComment param);

}
