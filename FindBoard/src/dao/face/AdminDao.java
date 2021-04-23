package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dto.DiscoverBoard;
import dto.FindBoard;
import dto.Product;
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

	/**
	 * findboard 테이블을 전체 조회한다.
	 * @param conn		DB 연결 객체
	 * @param apaging	페이징 정보 객체
	 * @return	findboard 테이블의 전체 목록
	 */
	List<FindBoard> selectFindBoard(Connection conn, AdminPaging apaging);

	/**
	 * 글번호로 찾기 게시판의 글을 삭제한다.
	 * @param conn		DB 연결 객체
	 * @param findno	게시글 번호
	 * @return	업데이트 행
	 */
	int deleteFindByFindno(Connection conn, int findno);

	/**
	 * product 테이블을 전체 조회한다.
	 * @param conn		DB 연결 객체
	 * @param apaging	페이징 정보 객체
	 * @return	product 테이블 전체 데이터
	 */
	List<Product> selectProduct(Connection conn, AdminPaging apaging);

	/**
	 * product 테이블에서 같은 카테고리를 가진 행을 조회한다.
	 * @param conn			DB 연결 객체
	 * @param categoryId	카테고리ID
	 * @return	조회 결과 목록 리스트
	 */
	List<Product> selectProductByCateId(Connection conn, int categoryId);

	/**
	 * 상품 ID로 상품 DB를 삭제한다.
	 * @param conn		DB 연결 객체
	 * @param product	상품 ID가 담긴 Product 개체
	 * @return	업데이트 행
	 */
	int deleteProdByCateId(Connection conn, Product product);

	/**
	 * findboard 테이블에서 pet, loc 값과 일치하는 목록을 조회한다.
	 * @param conn		DB 연결 객체
	 * @param map		pet, loc 값을 가진 Map
	 * @return			조회 결과 리스트
	 */
	List<FindBoard> selectFindByMap(Connection conn, Map<String, String> map);

	/**
	 * findboard 테이블에서 pet 값이 일치하는 목록을 조회한다.
	 * @param conn		DB 연결 객체
	 * @param pet		pet 값을 가진 전달 파라미터
	 * @return			조회 결과 리스트
	 */
	List<FindBoard> selectFindByPet(Connection conn, String pet);

	/**
	 * findboard 테이블에서 loc 값이 일치하는 목록을 조회한다.
	 * @param conn		DB 연결 객체
	 * @param loc		loc 값을 가진 전달 파라미터
	 * @return			조회 결과 리스트
	 */
	List<FindBoard> selectFindByLoc(Connection conn, String loc);

	/**
	 * discoverboard 테이블을 전체 조회한다.
	 * @param conn		DB 연결 객체
	 * @param apaging	페이징 정보 객체
	 * @return			조회 결과 리스트
	 */
	List<DiscoverBoard> selectDiscoverBoard(Connection conn, AdminPaging apaging);

	/**
	 * discoverboard 테이블에서 pet, loc 값과 일치하는 목록을 조회한다.
	 * @param conn	DB 연결 객체
	 * @param map	pet, loc 값을 가진 Map
	 * @return		조회 결과 리스트
	 */
	List<DiscoverBoard> selectDiscByMap(Connection conn, Map<String, String> map);

	/**
	 * discoverboard 테이블에서 pet 값이 일치하는 목록을 조회한다.
	 * @param conn	DB 연결 객체
	 * @param pet	pet 값을 가진 전달 파라미터
	 * @return		조회 결과 리스트
	 */
	List<DiscoverBoard> selectDiscByPet(Connection conn, String pet);

	/**
	 * discoverboard 테이블에서 loc 값이 일치하는 목록을 조회한다.
	 * @param conn	DB 연결 객체
	 * @param loc	loc 값을 가진 전달 파라미터
	 * @return		조회 결과 리스트
	 */	
	List<DiscoverBoard> selectDiscByLoc(Connection conn, String loc);


	
	



}
