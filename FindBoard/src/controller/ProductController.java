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
import util.ProductPaging;

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ProductPaging paging = productService.getPaping(req);
		
		System.out.println("/product 의 paging = " + paging);
		
		
//List<ProductImg> productImg = (List)request.getAttribute("productImg");
	
		
		List<Product> product = productService.getList(paging);
		
		System.out.println("/product 의 productImg = " + product);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("product", product);
		
//		List<ProductImg> productImg = productService.viewMainImg(product);
		
//		System.out.println( "전달할 상품 이미지 정보 = " + productImg);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/product/productmain.jsp")
			.forward(req, resp);

	}
	
						
			
	
}
