package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import dto.Product;
import dto.ProductImg;
import util.ProductPaging;

public interface ProductService {
	
	/**
	 * 상품페이지의 페이징얻기
	 * @param req
	 * @return
	 */
	ProductPaging getPaping(HttpServletRequest req);
	
	/**
	 * 상품의 페이지 리스트 정보
	 * @param paging
	 * @return
	 */
	List<Product> getList(ProductPaging paging);

	/**
	 * 상품 ID추출
	 * @param req	상품 ID가 담긴 요청 파라미터
	 * @return	상품 데이터를 저장한 Product 객체
	 */
	Product getProdByProdId(HttpServletRequest req);

	/**
	 * 추출한 상품 ID로 게시글 조회
	 * @param productid 추출한 상품 id
	 * @return
	 */
	Product views(Product productid);
	
	
	/**
	 * 상품의 이미지 정보 얻어오기
	 * @param viewProduct 얻어올 상품 정보
	 * @return
	 */
	List<ProductImg> viewImg(Product viewProduct);

	
	/**
	 * 상품을 등록한다.
	 * @param req	상품 정보가 담긴 요청 파라미터
	 */
	void write(HttpServletRequest req);
	
	/**
	 * 
	 * @param product
	 * @return
	 */
	List<ProductImg> viewMainImg(List<Product> product);
	


}
