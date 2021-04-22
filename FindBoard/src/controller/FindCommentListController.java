package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.FindComment;
import service.face.FindBoardService;
import service.impl.FindBoardServiceImpl;


@WebServlet("/find/commentlist")
public class FindCommentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private FindBoardService findBoardService = new FindBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("find/commentlist - [GET] 요청");
		
		Gson gson = new Gson();
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		
		String param = req.getParameter("findNo");
		
		System.out.println("/find/commentlist param = " + param);
		
		
		int findNo = 0;
		if( param != null && !"".equals(param)) {
			
			findNo = Integer.parseInt(param);
			
		}
		
		System.out.println("/find/commentlist findNo = "+ findNo);
		List<FindComment> findcommentlist = findBoardService.viewComment(findNo);
		
		PrintWriter out = resp.getWriter();
		
		out.print(gson.toJson(findcommentlist));
		
		
		
	}
	
	
	
	
	

}
