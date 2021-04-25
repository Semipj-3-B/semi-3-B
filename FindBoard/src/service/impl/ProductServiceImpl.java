package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ProductDao;
import dao.impl.ProductDaoImpl;
import dto.Product;
import dto.ProductImg;
import service.face.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao = new ProductDaoImpl();
	
	@Override
	public Product getProdByProdId(HttpServletRequest req) {
		
		//productId를 저장할 객체 생성
		Product productId = new Product();
		
		//전달된 ProductId 검증하기
		String param = req.getParameter("ProductId");
		
		System.out.println("ProductId: " + param);
		

		if(param != null && !"".equals(param)) {
			
			//파라미터 추출
			productId.setProductId( Integer.parseInt(param) );
		}
		
		//결과 반환
		return productId;
	}

	@Override
	public Product views(Product productid) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//상품상세글 조회
		Product productview = productDao.selectProdByProdId(conn, productid);
		
		return productview;
	}

	@Override
	public List<ProductImg> viewImg(Product viewProduct) {
		
		return productDao.selectImg(JDBCTemplate.getConnection(), viewProduct);
	}

}
