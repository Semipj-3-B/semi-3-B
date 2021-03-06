package dao.face;

import java.sql.Connection;

import java.util.List;
import java.util.Map;

import dto.FindBoard;
import dto.FindComment;
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
	public List<FindBoard> selectAll(Connection conn, Paging paging, Map<String, String> map);

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
	public FindBoard selectBoardByFindno(Connection conn, FindBoard findno);



	/**
	 *  
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
//	public FindBoard selectFind(Connection conn, FindBoard findNo);

	/**
	 * 닉네임 userNo 통해 얻어오기
	 * @param connection
	 * @param viewFindBoard
	 * @return
	 */
	public String selectNickByUserNo(Connection conn, FindBoard viewFindBoard);
	
	/**
	 * 
	 * userNo로 email 얻어오기
	 * 
	 * @param conn
	 * @param viewFindBoard
	 * @return
	 */
	public String selectEmailByUserNo(Connection conn, FindBoard viewFindBoard);

	/**
	 * 첨부파일 조회
	 * 
	 * @param connection
	 * @param viewFindBoard
	 * @return
	 */
	public List<FindImg> selectFile(Connection connection, FindBoard viewFindBoard);
	
	/**
	 * FindBoard 테이블 시퀀스의 nextval을 조회한다.
	 * @param conn	DB 연결 객체
	 * @return	findboard_seq.nextval
	 */
	public int selectFindno(Connection conn);

	
	/**
	 * 새 게시글의 데이터를 FindBoard 테이블에 삽입한다.
	 * @param conn		DB 연결 객체
	 * @param findBoard	새 게시글 데이터가 담긴 전달 파라미터
	 * @return		삽입된 행의 수
	 */
	public int insert(Connection conn, FindBoard findBoard);

	
	/**
	 * 새 게시글의 첨부파일을 FingImg 테이블에 삽입한다.
	 * @param conn		DB 연결 객체
	 * @param findImges	첨부파일 데이터가 담긴 전달 파라미터
	 * @return		삽입된 행의 수
	 */
	public int insertImg(Connection conn, List<FindImg> findImges);


	/**
	 * 새 게시글을 작성한 회원의 아이디로 회원번호를 조회한다.
	 * @param conn		DB 연결 객체
	 * @param userid	회원의 아이디 데이터가 담긴 전달 파라미터
	 * @return 		회원번호 조회
	 */
	public int selectUserno(Connection conn, String userid);

	
	/**
	 * 파일 삭제
	 * @param conn
	 * @param findboard
	 * @return
	 */
	public int deleteFile(Connection conn, FindBoard findboard);

	
	/**
	 * 게시글 삭제
	 * @param conn
	 * @param findboard
	 * @return
	 */
	public int delete(Connection conn, FindBoard findboard);

	/**
	 * 게시글 수정
	 * @param conn
	 * @param findboard
	 * @return
	 */
	public int update(Connection conn, FindBoard findboard);


	/**
	 * 댓글 찾기
	 * 
	 * @param conn DB연결 객체
	 * @param findNo 댓글을 찾을 게시글 번호
	 * @return
	 */
	public List<FindComment> selectComment(Connection conn, int findNo);

	
	/**
	 * 작성한 댓글 정보를  DB에 입력
	 * @param conn
	 * @param param
	 * @return
	 */
	public int insertComment(Connection conn, FindComment param);

	
	/**
	 * 댓글 삭제하기
	 * @param conn
	 * @param param
	 * @return
	 */
	public int deleteComment(Connection conn, FindComment param);

	
	/**
	 * 댓글 수정하기 DAO
	 * 
	 * @param conn
	 * @param param
	 * @return
	 */
	public int updateComment(Connection conn, FindComment param);

	
//	/**
//	 * 수정시 기존 이미지 파일 삭제
//	 * @param conn
//	 * @param isNewFile
//	 */
//	public void deleteFile(Connection conn, boolean isNewFile);



	




	


}
