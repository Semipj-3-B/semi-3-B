package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.FindBoard;
import dto.FindImg;

public interface FindBoardService {

	/**
	 * 요청 파라미터 얻기
	 * 
	 * @param req	요청 정보 객체
	 * @return	전달 파라미터
	 */
	public FindBoard getParam(HttpServletRequest req);
	
	
	/**
	 * findNo를 이용하여 게시글 조회
	 * + 조회수 증가
	 * 
	 * @param findNo -> findNo를 가지고 있는 객체
	 * @return	조회된 찾기 게시글
	 */
	public FindBoard read(FindBoard findNo);

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
