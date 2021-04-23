package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ReviewBoard;
import dto.ReviewDetailView;
import dto.ReviewImgFile;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

@WebServlet("/review/view")
public class ReviewTextViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("리뷰 상세보기 [GET]");
		
		HttpSession session = req.getSession();
		if(session.getAttribute("login") == null) {
			resp.sendRedirect("/login/login");
		} else {
		
		
			//reviewNo 전달파라미터 얻기
			ReviewBoard reviewNo = reviewService.getReviewNo(req);
	//		System.out.println(reviewNo);
			
			//상세보기 결과 조회
			ReviewDetailView reviewTextview = reviewService.view(reviewNo);
	//		System.out.println(reviewTextview);
			
			//조회결과 MODEL값 전달
			req.setAttribute("reviewTextView", reviewTextview);
			
			//첨부파일 정보 VIEW에 전달
			List<ReviewImgFile> reviewImgs = reviewService.viewFile(reviewNo);
			req.setAttribute("reviewImgs", reviewImgs);
	//		System.out.println(reviewImgs);
			
			req.setAttribute("reviewNo", reviewNo);
			
			req.getRequestDispatcher("/WEB-INF/views/review/view.jsp").forward(req, resp);
		}
	}

}
