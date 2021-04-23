package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Product;

public interface ProductService {

	/**
	 * 상품 ID로 상품 데이터를 가져온다.
	 * @param req	상품 ID가 담긴 요청 파라미터
	 * @return	상품 데이터를 저장한 Product 객체
	 */
	Product getProdByProdId(HttpServletRequest req);

}
