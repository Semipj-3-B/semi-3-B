package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Product;
import dto.ProductImg;


public interface ProductDao {

	
	/**
	 * 상품 ID로 데이터를 조회한다.
	 * @param conn		DB 연결 객체
	 * @param productId	상품 ID
	 * @return	조회 결과
	 */
	Product selectProdByProdId(Connection conn, Product productid);
	
	/**
	 * 조회된 상품 데이터로 상품 이미지 조회
	 * 
	 * @param connection
	 * @param viewProduct
	 * @return
	 */
	List<ProductImg> selectImg(Connection connection, Product viewProduct);

}
