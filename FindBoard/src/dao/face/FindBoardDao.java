package dao.face;

import java.sql.Connection;

import java.util.List;

import dto.FindBoard;
import dto.FindImg;
import util.Paging;

public interface FindBoardDao {

	/**
	 * FindBoard테이블 전체 조회 (페이징 없음)
	 * 
	 * @return List<FindBoard> - FindBoard테이블 전체 조회 결과 리스트
	 */
	public List<FindBoard> selectAll(Connection conn);
	
	
	/**
	 * FindBoard테이블 전체 조회
	 * 페이징 처리 추가
	 * 
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<FindBoard> - FindBoard테이블 전체 조회 결과 리스트
	 */
	public List<FindBoard> selectAll(Connection conn, Paging paging);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @return 총 게시글 수
	 */
	public int selectCntAll(Connection connection);

	/**
	 * 특정 게시글 조회
	 * 
	 * @param find_no - 조회할 find_no 가진 객체
	 * @return FindBoard - 조회된 결과 객체
	 */
	public FindBoard selectBoardByFindno(Connection conn, FindBoard find_no);

	/**
	 * 조회된 게시글 조회수 증가
	 * 
	 * @param find_no - 조회된 게시글 번호를 가진 객체
	 */
	public int updateViews(Connection conn, FindBoard find_no);

	/**
	 *  선택한 게시글 조회
	 *  
	 * @param conn
	 * @param findNo
	 * @return
	 */
	public int updateHit(Connection conn, FindBoard findNo);
	
	/**
	 * 게시글 조회
	 * 
	 * @param conn
	 * @param findNo
	 * @return
	 */
	public FindBoard selectFind(Connection conn, FindBoard findNo);

	/**
	 * 닉네임 userNo 통해 얻어오기
	 * @param connection
	 * @param viewFindBoard
	 * @return
	 */
	public String selectNickByUserNo(Connection connection, FindBoard viewFindBoard);

	/**
	 * 첨부파일 조회
	 * 
	 * @param connection
	 * @param viewFindBoard
	 * @return
	 */
	public FindImg selectFile(Connection connection, FindBoard viewFindBoard);
	
	/**
	 * FindBoard 테이블 시퀀스의 nextval을 조회한다.
	 * @param conn	DB 연결 객체
	 * @return	findboard_seq.nextval
	 */
	int selectFindno(Connection conn);

	
	/**
	 * 새 게시글의 데이터를 FindBoard 테이블에 삽입한다.
	 * @param conn		DB 연결 객체
	 * @param findBoard	새 게시글 데이터가 담긴 전달 파라미터
	 * @return		삽입된 행의 수
	 */
	int insert(Connection conn, FindBoard findBoard);

	
	/**
	 * 새 게시글의 첨부파일을 FingImg 테이블에 삽입한다.
	 * @param conn		DB 연결 객체
	 * @param findImg	첨부파일 데이터가 담긴 전달 파라미터
	 * @return		삽입된 행의 수
	 */
	int insertImg(Connection conn, FindImg findImg);
	


}