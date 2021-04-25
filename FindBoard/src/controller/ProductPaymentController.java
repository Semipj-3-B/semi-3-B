package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import service.face.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet("/product/pay")
public class ProductPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/product/productpay.jsp").forward(req, resp);
		
		//상품 아이디로 상품정보 얻어오기
		Product productid = productService.getProdByProdId(req);
		
	}
	

}
