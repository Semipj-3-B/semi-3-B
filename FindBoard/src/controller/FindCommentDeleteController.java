package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FindComment;
import service.face.FindBoardService;
import service.impl.FindBoardServiceImpl;


@WebServlet("/find/commentdelete")
public class FindCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private FindBoardService findBoardService = new FindBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FindComment param = findBoardService.getCommentParam(req);
		
		findBoardService.removeComment(param);
		
	}

}
