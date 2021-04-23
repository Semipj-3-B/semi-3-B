package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.ReviewBoard;
import dto.ReviewDetailView;
import dto.ReviewImgFile;
import dto.ReviewUserJoin;
import util.Paging;

public interface ReviewDao {

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param connection
	 * @return
	 */
	public int selectCntAll(Connection connection);

	/**
	 * ReviewBoard 테이블 전체 조회
	 * 
	 * @param connection
	 * @param paging
	 * @return
	 */
	public List<ReviewUserJoin> selectAll(Connection connection, Paging paging);

	/**
	 * 다음 게시글 번호 반환
	 * 시퀀스 통해 추출
	 * 
	 * @param conn
	 * @return - 다음 게시글 번호
	 */
	public int selectReviewNo(Connection conn);

	/**
	 * 게시글 삽입
	 * 
	 * @param conn
	 * @param reviewBoard
	 * @return
	 */
	public int insert(Connection conn, ReviewBoard reviewBoard);

	/**
	 * 이미지 첨부파일 삽입
	 * 
	 * @param conn
	 * @param reviewImgFile
	 * @return
	 */
	public int insertImgFile(Connection conn, List<ReviewImgFile> reviewImgs);

	/**
	 * reviewNo로 게시글 조회
	 * 
	 * @param conn
	 * @param reviewNo
	 * @return
	 */
	public ReviewDetailView selectReviewBoardByReviewNo(Connection conn, ReviewBoard reviewNo);

	/**
	 * 조회된 게시글 조회수 증가
	 * 
	 * @param conn
	 * @param reviewNo - 조회된 리뷰 글 번호 가진 객체
	 * @return
	 */
	public int updateViews(Connection conn, ReviewBoard reviewNo);

	/**
	 * 첨부파일 조회
	 * 
	 * @param connection
	 * @param reviewTextview
	 * @return
	 */
	public List<ReviewImgFile> selectReviewImgs(Connection conn, ReviewBoard reviewNo);

	/**
	 * 파일 삭제
	 * 
	 * @param conn
	 * @param reviewBoard
	 */
	public int deleteImgFile(Connection conn, ReviewBoard reviewBoard);

	/**
	 * 게시글 수정
	 * 
	 * @param conn
	 * @param reviewBoard
	 * @return
	 */
	public int update(Connection conn, ReviewBoard reviewBoard);

	/**
	 * 수정된 게시글의 새로운 첨부파일 삽입
	 * 
	 * @param conn
	 * @param reviewImgs
	 * @return
	 */
	public int insertImg(Connection conn, List<ReviewImgFile> reviewImgs);

	/**
	 * 파일 삭제
	 * 
	 * @param conn
	 * @param reviewNo - 리뷰넘버
	 * @return
	 */
	public int deleteFile(Connection conn, ReviewBoard reviewNo);

	/**
	 * 게시글 삭제
	 * 
	 * @param conn
	 * @param reviewNo
	 * @return
	 */
	public int delete(Connection conn, ReviewBoard reviewNo);

}
