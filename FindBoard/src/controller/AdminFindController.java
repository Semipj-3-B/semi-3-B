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

import dto.FindBoard;
import service.face.AdminService;
import service.impl.AdminServiceImpl;
import util.AdminPaging;

@WebServlet("/admin/find")
public class AdminFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Gson gson = null;
		List<FindBoard> findList = null;
		
		boolean flag = adminService.isAjaxReq(req);
		if(flag) {
			gson = new Gson();
			Map<String, String> map = new HashMap<String, String>();
			map.put("pet", req.getParameter("pet"));
			map.put("loc", req.getParameter("loc"));
			findList = adminService.getFindListByMap(map);
			
			resp.setContentType("application/json; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(findList));
			
		} else {
			AdminPaging apaging = adminService.getPaging(req);
			findList = adminService.getFindList(apaging);
			req.setAttribute("apaging", apaging);
			req.setAttribute("findList", findList);
			req.getRequestDispatcher("/WEB-INF/views/admin/find.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		adminService.deleteFind(req);
		resp.sendRedirect("/admin/find");
	}
}
