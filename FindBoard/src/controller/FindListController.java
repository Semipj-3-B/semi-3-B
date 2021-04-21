package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FindBoard;
import service.face.FindBoardService;
import service.impl.FindBoardServiceImpl;
import util.Paging;


@WebServlet("/find/list")
public class FindListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FindBoardService findboardService = new FindBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("findListController - GET");
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = findboardService.getPaging(req);
		System.out.println("findListController - " + paging);
		
		
		String pet = req.getParameter("pet");
		String loc = req.getParameter("loc");
		
		Map<String, String> map = new HashMap<>();
		map.put("pet", pet);
		map.put("loc", loc);


		//게시글 전체 조회
//		List<FindBoard> findList = findboardService.getList();
		
		//페이징을 적용한 게시글 전체 조회
		List<FindBoard> findboardList = findboardService.getList(paging,map);
		

		//페이징 객체를 MODEL값으로 전달
		req.setAttribute("paging", paging);
		
//		//조회결과 MODEL값 전달
		req.setAttribute("findList", findboardList);
//		
//		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/find/list.jsp").forward(req, resp);
				
		
	}
}
