package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Usertb;
import service.face.AdminService;
import service.impl.AdminServiceImpl;

@WebServlet("/admin/user")
public class AdminUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		List<Usertb> userList = adminService.getList();
		
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("/adminData/adminUser.jsp").forward(req, resp);
	}
}
