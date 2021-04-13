package dao.face;

import java.sql.Connection;

import dto.FindBoard;
import dto.FindImg;

public interface FindBoardDao {
	
	
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
