package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.SignUpService;
import service.impl.SignUpServiceImpl;

@WebServlet("/login/email")
public class SignUpEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//SingUpService 객체 생성
	private SignUpService signUpService = new SignUpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터값 받아오기
		String email = req.getParameter("email");
		
		//인증번호 이메일 발송
		String atnum = signUpService.sendEmail(email);
		
		//인증번호 TEST
		System.out.println("인증번호 : " + atnum);
		
		//응답출력 스트림
		PrintWriter out = resp.getWriter();
		
		//인증번호 VIEW로 전송
		out.print(atnum);
	}
}
