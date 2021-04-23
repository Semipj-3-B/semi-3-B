package dao.face;

import java.sql.Connection;

import dto.Product;

public interface ProductDao {

	/**
	 * 상품 ID로 데이터를 조회한다.
	 * @param conn		DB 연결 객체
	 * @param productId	상품 ID
	 * @return	조회 결과
	 */
	Product selectProdByProdId(Connection conn, int productId);

}
