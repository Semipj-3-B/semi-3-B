package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.DiscoverBoard;
import service.face.AdminService;
import service.impl.AdminServiceImpl;
import util.AdminPaging;

@WebServlet("/admin/discover")
public class AdminDiscoverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Gson gson = null;
		List<DiscoverBoard> discList = null;
		
		boolean flag = adminService.isAjaxReq(req);
		if(flag) {
			gson = new Gson();
			Map<String, String> map = new HashMap<String, String>();
			map.put("pet", req.getParameter("pet"));
			map.put("loc", req.getParameter("loc"));
			discList = adminService.getDiscListByMap(map);
			
			resp.setContentType("application/json; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(discList));
			
		} else {
			AdminPaging apaging = adminService.getPaging(req);
			discList = adminService.getDiscList(apaging);
			req.setAttribute("apaging", apaging);
			req.setAttribute("discList", discList);
			req.getRequestDispatcher("/WEB-INF/views/admin/discover.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		adminService.deleteFind(req);
		resp.sendRedirect("/admin/find");
	}
}
