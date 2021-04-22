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


@WebServlet("/find/commentinsert")
public class FindCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FindBoardService findBoardService = new FindBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("find/commentinsert - [POST] 요청");
		req.setCharacterEncoding("UTF-8");
		
		FindComment param = findBoardService.getCommentParam(req);
		
		findBoardService.writeComment(param);
		
		
//		System.out.println("DAO까지 거친 결과값 = "  );
		
	}

}
