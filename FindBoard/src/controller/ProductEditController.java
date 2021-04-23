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

@WebServlet("/product/edit")
public class ProductEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Product product = productService.getProdByProdId(req);
		req.setAttribute("product", product);
		req.getRequestDispatcher("/WEB-INF/product/edit.jsp").forward(req, resp);
	
	}

}
