package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.FindBoard;

public interface FindBoardService {

	/**
	 * 작성된 게시글의 데이터를 FindBoard 객체로 저장한다.
	 * @param req	작성된 게시글 데이터를 담은 요청 파라미터
	 */
	void write(HttpServletRequest req);

}
