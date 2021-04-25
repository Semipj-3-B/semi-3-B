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

	/**
	 * Product 테이블 시퀀스의 nextval을 조회한다.
	 * @param conn	DB 연결 객체
	 * @return	 product_seq.nextval
	 */
	int selectproductId(Connection conn);

	/**
	 * 새로 등록한 상품의 데이터를 Product 테이블에 삽입한다.
	 * @param conn		DB 연결 객체
	 * @param product	상품 정보가 담긴 전달 파라미터
	 * @return		삽입된 행의 수
	 */
	int insert(Connection conn, Product product);

	/**
	 * 새로 등록한 상품에 첨부된 파일의 이름을 Product_img 테이블에 삽입한다.
	 * @param conn			DB 연결 객체
	 * @param productImgs	첨부파일 이름이 담긴 전달 파라미터
	 * @return		삽입된 행의 수
	 */
	int insertImg(Connection conn, List<ProductImg> productImgs);

}
