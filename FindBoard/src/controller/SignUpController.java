package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Usertb;
import service.face.SignUpService;
import service.impl.SignUpServiceImpl;

@WebServlet("/login/signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//SingUpService 객체 생성
	private SignUpService signUpService = new SignUpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/login/SignUp.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 데이터의 한글 인코딩 설정
		req.setCharacterEncoding("UTF-8");
		
		//입력정보 받아오기
		Usertb user = signUpService.getParam(req);

		//회원가입 
		int res = signUpService.signUpUser(user);
		
		if(res > 0) {
			//회원가입 완료시 메인으로 리다이렉트
			resp.sendRedirect("/main");
		}else {
			//회원가입 실패시 에러페이지로 이동
			req.getRequestDispatcher("/WEB-INF/views/layout/Error.jsp").forward(req, resp);
		}
	
	}
}
