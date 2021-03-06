package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.FindBoard;
import dto.FindComment;
import dto.FindImg;
import util.Paging;


//import controller.Paging;

public interface FindBoardService {

	/**
	 * 게시글 전체 조회하기
	 * 페이징 없음
	 * @param paging 
	 * 
	 * @return List<FindBoard> - 게시글 전체 조회 결과 리스트
	 */
	public List<FindBoard> getList(); 
	
	
	/**
	 * 게시글 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<FindBoard> - 게시글 전체 조회 결과 리스트
	 */
	public List<FindBoard> getList(Paging paging, Map<String,String> map);

	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * Board 테이블과 curPage 값을 이용하여 Paging객체를 생성한다
	 * 
	 * @param req - curPage정보를 담고 있는 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 *  find_no를 이용하여 게시글 조회
	 * 	
	 * 
	 * @param find_no  - find_no를 가지고 있는 객체
	 * @return FindBoard - 조회된 게시글
	 */
	public FindBoard views(FindBoard findno);

	/**
	 * 요청 파라미터 얻기
	 * 
	 * @param req	요청 정보 객체
	 * @return	전달 파라미터
	 */
	public FindBoard getParam(HttpServletRequest req);
	


	/**
	 * FindBoard의 userid를 이용해 닉네임 조회
	 * 
	 * @param viewFindBoard 조회할 게시글
	 * @return
	 */
	public String getnick(FindBoard viewFindBoard);

	/**
	 * 첨부파일의 정보 얻어오기
	 * @param viewFindBoard 첨부파일이 포함된 게시글 번호
	 * @return FindImg - 첨부파일 정보
	 */
	public List<FindImg> viewFile(FindBoard viewFindBoard);
	
	/**
	 * 작성된 게시글의 데이터를 FindBoard 객체로 저장한다.
	 * @param req	작성된 게시글 데이터를 담은 요청 파라미터
	 */
	void write(HttpServletRequest req);

	/**
	 * 이메일 얻어오기
	 * @param viewFindBoard 조회할 게시글
	 * @return 이메일
	 */
	public String getemail(FindBoard viewFindBoard);

	
	/**
	 * 게시글 삭제
	 * @param findboard
	 */
	public void delete(FindBoard findboard);


	/**
	 * 게시글 수정
	 * @param req
	 */
	public void update(HttpServletRequest req);


	
	/**
	 * findboard의 댓글 보여주기
	 * @param findNo 댓글이 나올 게시글 번호
	 * @return
	 */
	public List<FindComment> viewComment(int findNo);

	
	/**
	 * 정보들 얻어오기
	 * @param req
	 * @return
	 */
	public FindComment getCommentParam(HttpServletRequest req);

	/**
	 * 댓글 작성하기
	 * @param param
	 */
	public void writeComment(FindComment param);

	
	/**
	 * 댓글 삭제하기
	 * @param param
	 */
	public void removeComment(FindComment param);

	
	/**
	 * 댓글 수정하기
	 * @param param
	 */
	public void updateComment(FindComment param);

	


	

}
