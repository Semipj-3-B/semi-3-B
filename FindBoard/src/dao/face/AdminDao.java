package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Usertb;
import util.AdminPaging;
import util.Paging;

public interface AdminDao {

	/**
	 * Usertb를 전체 조회한다.
	 * @param conn		DB 연결 객체
	 * @return		Usertb 테이블의 모든 데이터
	 */
	List<Usertb> selectAll(Connection conn);

	/**
	 * Usertb의 전체 행을 조회한다.
	 * @param conn	DB 연결 객체
	 * @return		Usertb의 전체 행
	 */
	int selectCntAll(Connection conn);

	/**
	 * 페이징 정보만큼 Usertb의 행을 조회한다.
	 * @param conn		DB 연결 객체
	 * @param apaging	페이징 정보 객체
	 * @return		DB 조회 결과
	 */
	List<Usertb> selectAll(Connection conn, AdminPaging apaging);

	/**
	 * 회원번호로 회원의 정보를 삭제한다.
	 * @param conn		DB 연결 객체
	 * @param userno	회원번호
	 * @return		업데이트 행
	 */
	int deleteUserByUserno(Connection conn, int userno);
	
	



}
