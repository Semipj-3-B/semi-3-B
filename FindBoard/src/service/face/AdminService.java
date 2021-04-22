package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.FindBoard;
import dto.Product;
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
	 * @param req	찾기 게시판의 게시글 번호가 담긴 객체
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
	 * @param apaging	페이징 정보 객체
	 * @param p	카테고리ID를 저장한 전달 파라미터
	 * @return	같은 카테고리의 상품 목록
	 */
	List<Product> getProdListByCateId(AdminPaging apaging, Product p);

}
