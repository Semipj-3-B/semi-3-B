package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FindBoard;
import service.face.FindBoardService;
import service.impl.FindBoardServiceImpl;


@WebServlet("/find/delete")
public class FIndDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FindBoardService findboardService = new FindBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/find/delete -[GET]");
		
		//삭제할 findno구하기
		FindBoard findboard = findboardService.getParam(req);
		
		System.out.println(findboard);
		
		//삭제하기
		findboardService.delete(findboard);
		
		//리스트로 다시 돌아가기
		resp.sendRedirect("/find/list");
		
	}

}
