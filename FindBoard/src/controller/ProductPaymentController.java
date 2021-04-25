package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import dto.ProductImg;
import service.face.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet("/product/pay")
public class ProductPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//상품 아이디로 상품정보 얻어오기
		Product productid = productService.getProdByProdId(req);
		
		System.out.println("productid 확인 = " + productid);
		
		//게시글번호로 게시글 상세보기
		Product viewProduct = productService.views(productid);
		
		System.out.println("게시글 번호로 게시글 상세보기정보 = " + viewProduct);
		
		//상세보기 조회결과 전달
		req.setAttribute("viewProduct", viewProduct);
		
		List<ProductImg> productImg = productService.viewImg(viewProduct);
		
		System.out.println( "전달할 상품 이미지 정보 = " + productImg);
		
		//이미지 전달 
		req.setAttribute("productImg", productImg);
		
		req.getRequestDispatcher("/WEB-INF/views/product/productpay.jsp")
			.forward(req, resp);
	
	}
	

}
