package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		//세션 무효화
		session.invalidate();
		
		Cookie[] cookies = req.getCookies();
		
		for(Cookie c : cookies) {
			if("autologin".equals(c.getName())) {
				c.setMaxAge(0);
				c.setPath("/");
				resp.addCookie(c);
			}
			if("userid".equals(c.getName())) {
				c.setMaxAge(0);
				c.setPath("/");
				resp.addCookie(c);
			}
		}
		
		resp.sendRedirect("/main");
		
		
	}

}
