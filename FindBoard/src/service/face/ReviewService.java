package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.ReviewBoard;
import dto.ReviewComment;
import dto.ReviewDetailView;
import dto.ReviewImgFile;
import dto.ReviewUserJoin;
import util.Paging;

public interface ReviewService {

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req
	 * @return
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 게시글 전체 조회 (페이징 처리)
	 * 
	 * @param paging
	 * @return
	 */
	public List<ReviewUserJoin> getList(Paging paging);

	/**
	 * 리뷰 게시글 작성
	 * 입력한 내용 DB 저장 
	 * 
	 * @param req - 요청정보 객체 (게시글 내용 + 첨부파일)
	 */
	public void reviewWrite(HttpServletRequest req);

	/**
	 * 요청 파라미터 얻기
	 * 
	 * @param req
	 * @return
	 */
	public ReviewBoard getReviewNo(HttpServletRequest req);

	/**
	 * 주어진 reviewNo로 게시글 조회
	 * 조회된 게시글 조회수 1 증가
	 * 
	 * @param reviewNo
	 * @return
	 */
	public ReviewDetailView view(ReviewBoard reviewNo);

	/**
	 * 첨부파일 정보 얻어오기
	 * 
	 * @param reviewTextview
	 * @return
	 */
	public List<ReviewImgFile> viewFile(ReviewBoard reviewNo);

	/**
	 * 게시글 수정
	 * 
	 * @param req
	 */
	public void update(HttpServletRequest req);

	/**
	 * 게시글 삭제
	 * 
	 * @param reviewNo
	 */
	public void delete(ReviewBoard reviewNo);

	/**
	 * 파라미터 얻어오기
	 * 
	 * @param req
	 * @return
	 */
	public ReviewComment getCommentParam(HttpServletRequest req);

	/**
	 * 댓글 쓰기
	 * 
	 * @param param
	 */
	public void writeComment(ReviewComment param);

	/**
	 * 조회한 글의 댓글 보여주기
	 * 
	 * @param reviewNo
	 * @return
	 */
	public List<ReviewComment> viewComment(int reviewNo);

	/**
	 * 댓글 삭제
	 * 
	 * @param param
	 */
	public void removeComment(ReviewComment param);

}
