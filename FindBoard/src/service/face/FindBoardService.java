package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.FindBoard;
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
	public List<FindBoard> getList(Paging paging);

	
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
	 * 	조회된 게시글의 조회수를 1증가시킨다
	 * 
	 * @param find_no  - find_no를 가지고 있는 객체
	 * @return FindBoard - 조회된 게시글
	 */
	public FindBoard views(FindBoard find_no);

	/**
	 * 요청 파라미터 얻기
	 * 
	 * @param req	요청 정보 객체
	 * @return	전달 파라미터
	 */
	public FindBoard getParam(HttpServletRequest req);
	
	
//	/**
//	 * findNo를 이용하여 게시글 조회
//	 * + 조회수 증가
//	 * 
//	 * @param findNo -> findNo를 가지고 있는 객체
//	 * @return	조회된 찾기 게시글
//	 */
//	public FindBoard read(FindBoard findNo);

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
	public FindImg viewFile(FindBoard viewFindBoard);
	
	/**
	 * 작성된 게시글의 데이터를 FindBoard 객체로 저장한다.
	 * @param req	작성된 게시글 데이터를 담은 요청 파라미터
	 */
	void write(HttpServletRequest req);
	

}
