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

import dto.DiscoverComment;
import service.face.DiscoverBoardService;
import service.impl.DiscoverBoardServiceImpl;


@WebServlet("/discover/commentlist")
public class DiscoverCommentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DiscoverBoardService discoverBoardService = new DiscoverBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		System.out.println("discover 댓글 리스트 확인");
		
		Gson gson = new Gson();
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		
		String param = req.getParameter("discoverNo");
		
		int discoverNo = 0;
		if( param != null && !"".equals(param)) {
			
			discoverNo = Integer.parseInt(param);
		}
		
		List<DiscoverComment> discovercommentlist = discoverBoardService.viewComment(discoverNo);
		
		PrintWriter out = resp.getWriter();
		
		out.print(gson.toJson(discovercommentlist));
		
		
	}
	
	
}
