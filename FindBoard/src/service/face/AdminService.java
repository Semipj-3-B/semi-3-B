package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.DiscoverBoard;
import dto.FindBoard;
import dto.Product;
import dto.ReviewUserJoin;
import dto.Usertb;
import util.AdminPaging;
import util.Paging;

public interface AdminService {

	/**
	 * 모든 회원의 목록과 정보를 가져온다.
	 * @return	모든 회원의 정보
	 */
	List<Usertb> getList();

	/**
	 * 페이징 객체 생성
	 * @param req 페이지 정보를 담은 객체
	 * @return	페이징 정보를 가진 객체
	 */
	AdminPaging getPaging(HttpServletRequest req);

	
	/**
	 * 페이징 처리를 추가하여 회원의 목록을 가져온다.
	 * @param apaging	페이징 정보 객체
	 * @return		페이징 처리된 회원 목록
	 */
	List<Usertb> getUserList(AdminPaging apaging);

	
	/**
	 * 회원번호로 탈퇴 처리를 진행한다. (데이터 삭제)
	 * @param req	회원번호가 담긴 요청 파라미터
	 */
	void withdraw(HttpServletRequest req);

	
	/**
	 * 페이징 처리와 함께 찾기 게시판의 게시글 목록을 가져온다.
	 * @param apaging	페이징 정보 객체
	 * @return	찾기 게시판의 전체 게시글 목록
	 */
	List<FindBoard> getFindList(AdminPaging apaging);

	/**
	 * 게시글을 삭제한다.
	 * @param req	찾기 게시판의 게시글 번호가 담긴 요청 파라미터
	 */
	void deleteFind(HttpServletRequest req);

	/**
	 * 페이징 처리하여 상품 목록 전체를 가져온다.
	 * @param apaging	페이징 정보 객체
	 * @return	전체 상품 목록
	 */
	List<Product> getProductList(AdminPaging apaging);

	/**
	 * 같은 카테고리의 상품 목록을 가져온다.
	 * @param categoryId	카테고리ID를 저장한 전달 파라미터
	 * @return	같은 카테고리의 상품 목록
	 */
	List<Product> getProdListByCateId(int categoryId);

	/**
	 * 상품을 삭제한다.
	 * @param req 상품 ID가 담긴 요청 파라미터
	 */
	void deleteProduct(HttpServletRequest req);

	/**
	 * ajax 요청 파라미터의 유무를 판별한다.
	 * @param req	요청 파라미터
	 * @return	ajax면 true, 아니면 false
	 */
	boolean isAjaxReq(HttpServletRequest req);

	/**
	 * pet 또는 loc에 따른 조회 데이터를 가져온다.
	 * @param map	pet, loc 데이터가 담긴 전달 파라미터
	 * @return		카테고리 값에 따른 조회 결과
	 */
	List<FindBoard> getFindListByMap(Map<String, String> map);

	/**
	 * 페이징 처리와 함께 발견 게시판의 게시글 목록을 가져온다.
	 * @param apaging	페이징 정보 객체
	 * @return	발견 게시판의 전체 게시글 목록
	 */
	List<DiscoverBoard> getDiscList(AdminPaging apaging);

	/**
	 * pet 또는 loc에 따른 조회 데이터를 가져온다.
	 * @param map	pet, loc 데이터가 담긴 전달 파라미터
	 * @return		카테고리 값에 따른 조회 결과
	 */
	List<DiscoverBoard> getDiscListByMap(Map<String, String> map);

	/**
	 * 페이징 처리와 함께 후기 목록을 가져온다.
	 * @param apaging	페이징 정보 객체
	 * @return	후기 게시글의 목록
	 */
	List<ReviewUserJoin> getReviewList(AdminPaging apaging);

	/**
	 * 후기 게시판에서 구분번호가 같은 게시글 목록을 가져온다.
	 * @param reviewSort	후기 게시판 구분 번호
	 * @return	같은 종류의 후기 게시글 목록
	 */
	List<ReviewUserJoin> getReviewBySort(int reviewSort);



}
