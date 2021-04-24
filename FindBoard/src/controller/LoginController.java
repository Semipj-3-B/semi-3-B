package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

	//LoginService 객체 생성
	private LoginService loginService = new LoginServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/login/Login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//입력정보 받아오기
		Usertb param = loginService.getParam(req);
		
		//입력정보와 유저정보가 일치하는지 조회
		boolean flag = loginService.login(param);
		
		String autologin = req.getParameter("autologin");
		String rememberid = req.getParameter("rememberid");
		
	
		if(flag) {
			//유저 정보 조회
			Usertb res = loginService.loginUser(param);
			
			//세션생성 및 로그인상태, id, userNo, Nickname 저장
			HttpSession session = req.getSession();
			session.setAttribute("login", true);
			session.setAttribute("userid", res.getUserId());
			session.setAttribute("userno", res.getUserNo());
			session.setAttribute("nick", res.getNick());
			
			//로그인 완료시 메인으로 리다이렉트
			resp.sendRedirect("/main?autologin=" + autologin + "&rememberid=" + rememberid);
			
		} else {
			//입력정보와 유저정보가 일치하지 않을시 메시지 전달
			req.setAttribute("msg", "아이디/비밀번호를 확인해 주세요");
			req.getRequestDispatcher("/WEB-INF/views/login/Login.jsp").forward(req,resp);
		}
		
	}
	
}
