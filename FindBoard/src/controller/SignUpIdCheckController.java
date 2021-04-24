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

@WebServlet("/login/idcheck")
public class SignUpIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//SignUpService 객체 생성
	private SignUpService signUpService = new SignUpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/login/signup").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터값 받아오기
		String id = req.getParameter("id");
		
		//중복된 id가있는지 조회
		int res = signUpService.signUpIDCheck(id);
		
		//응답출력 스트림
		PrintWriter out = resp.getWriter();
		
		//중복된 아이디가 있으면 1, 없으면 0 전달
		out.print(res);
	
	
	}

}
