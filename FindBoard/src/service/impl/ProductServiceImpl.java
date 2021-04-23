package service.impl;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ProductDao;
import dao.impl.ProductDaoImpl;
import dto.Product;
import service.face.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao = new ProductDaoImpl();
	
	@Override
	public Product getProdByProdId(HttpServletRequest req) {
		String param = req.getParameter("productId");
		System.out.println("productId: " + param);
		
		int productId = 0;
		if(param != null && !"".equals(param)) {
			productId = Integer.parseInt(param);
		}
		
		return productDao.selectProdByProdId(JDBCTemplate.getConnection(), productId);
	}

}
