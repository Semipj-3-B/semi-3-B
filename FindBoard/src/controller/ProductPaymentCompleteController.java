package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/product/paycomplete")
public class ProductPaymentCompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/product/paycomplete - [POST] 넘어옴");
		
		
		resp.sendRedirect("/product");
	}
	

}
