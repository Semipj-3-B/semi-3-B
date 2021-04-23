package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.DiscoverComment;
import service.face.DiscoverBoardService;
import service.impl.DiscoverBoardServiceImpl;


@WebServlet("/discover/commentdelete")
public class DiscoverCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private DiscoverBoardService discoverBoardService = new DiscoverBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DiscoverComment param = discoverBoardService.getCommentParam(req);
		
		discoverBoardService.removeComment(param);
		
		
	}
	
	
}
