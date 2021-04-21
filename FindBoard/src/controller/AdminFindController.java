package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FindBoard;
import dto.Usertb;
import service.face.AdminService;
import service.impl.AdminServiceImpl;
import util.AdminPaging;

@WebServlet("/admin/find")
public class AdminFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminPaging apaging = adminService.getPaging(req);
		List<FindBoard> findList = adminService.getFindList(apaging);
		
		req.setAttribute("findList", findList);
		req.setAttribute("apaging", apaging);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/find.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		adminService.deleteFind(req);
		resp.sendRedirect("/admin/find");
	}
}
