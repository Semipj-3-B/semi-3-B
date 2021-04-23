package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.DiscoverBoardService;
import service.impl.DiscoverBoardServiceImpl;


@WebServlet("/discover/add")
public class DiscoverAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DiscoverBoardService discoverBoardService = new DiscoverBoardServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/discover/add.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		discoverBoardService.write(req);
		
		resp.sendRedirect("/discover/list");
		
		
	}
	
	
}
