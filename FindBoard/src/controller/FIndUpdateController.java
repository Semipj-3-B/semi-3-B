package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FindBoard;
import dto.FindImg;
import service.face.FindBoardService;
import service.impl.FindBoardServiceImpl;


@WebServlet("/find/update")
public class FIndUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//FindBoardService 객체 생성
	private FindBoardService findboardService = new FindBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/find/update - [GET]요청");
		
		
		//전달파라미터 얻기 - findno
		FindBoard findno = findboardService.getParam(req);

		System.out.println("findno가 나오나"+findno);
				
		//상세보기 결과 
		FindBoard viewFindBoard = findboardService.views(findno);
				
		System.out.println("findno만 추출해서 나오나"+viewFindBoard);
				
				
		//닉네임 전달
		req.setAttribute("nick", findboardService.getnick(viewFindBoard));
				
		//이메일 전달
		req.setAttribute("email", findboardService.getemail(viewFindBoard));
				
		//조회결과 MODEL값 전달
		req.setAttribute("viewFindBoard", viewFindBoard);
				
				
				
		//첨부파일 정보 VIEW에 전달
		List<FindImg> findImg = findboardService.viewFile(viewFindBoard);
		req.setAttribute("findImg", findImg);
				
		System.out.println("findImg"+findImg);
		

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/find/update.jsp")
			.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/find/update - [POST] 요청완료");
		
		findboardService.update(req);
		
//		System.out.println("req"+req);
		
		resp.sendRedirect("/find/list");
		
	}

}
