package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Usertb;
import service.face.LoginService;
import service.impl.LoginServiceImpl;

@WebServlet("/login/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginService loginService = new LoginServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/login/Login.jsp").forward(req, resp);
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Usertb param = loginService.getParam(req);
	
		boolean flag = loginService.login(param);
	
		if(flag) {
			Usertb res = loginService.loginUser(param);
			
			HttpSession session = req.getSession();
			session.setAttribute("login", flag);
			session.setAttribute("userid", res.getUserId());
			session.setAttribute("nick", res.getNick());
			
			resp.sendRedirect("/main");
			
		} else {
			req.setAttribute("msg", "아이디/비밀번호를 확인해 주세요");
			req.getRequestDispatcher("/WEB-INF/views/login/Login.jsp").forward(req,resp);
		}
	}
	
}
