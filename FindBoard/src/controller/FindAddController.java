package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.FindBoardService;
import service.impl.FindBoardServiceImpl;

@WebServlet("/find/add")
public class FindAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FindBoardService findBoardService = new FindBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/find/add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		findBoardService.write(req);
		
		resp.sendRedirect("/find/list");
	}

}
