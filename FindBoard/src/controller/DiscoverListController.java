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
import javax.servlet.http.HttpSession;

import dto.DiscoverBoard;
import service.face.DiscoverBoardService;
import service.impl.DiscoverBoardServiceImpl;
import util.Paging;



@WebServlet("/discover/list")
public class DiscoverListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DiscoverBoardService discoverBoardService = new DiscoverBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("discoverListController - GET");
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = discoverBoardService.getPaging(req);
		System.out.println("discoverListController - " + paging);
		
		
		String pet = req.getParameter("pet");
		String loc = req.getParameter("loc");
		
		Map<String, String> map = new HashMap<>();
		map.put("pet", pet);
		map.put("loc", loc);
		
		
		List<DiscoverBoard> discoverboardList = discoverBoardService.getList(paging, map);
		
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("discoverList", discoverboardList);
		
		req.getRequestDispatcher("/WEB-INF/views/discover/list.jsp")
		.forward(req, resp);
		
	}
	

}
