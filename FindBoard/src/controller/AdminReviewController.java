package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.ReviewUserJoin;
import service.face.AdminService;
import service.impl.AdminServiceImpl;
import util.AdminPaging;

@WebServlet("/admin/review")
public class AdminReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		List<ReviewUserJoin> reviewList = null;
		
		String param = req.getParameter("reviewSort");
		if(param != null && !"".equals(param)) {
			Gson gson = new Gson();
			int reviewSort = Integer.parseInt(param);
			reviewList = adminService.getReviewBySort(reviewSort);
			
			resp.setContentType("application/json; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(reviewList));
			
		} else {
			AdminPaging apaging = adminService.getPaging(req);
			reviewList = adminService.getReviewList(apaging);
			
			req.setAttribute("apaging", apaging);
			req.setAttribute("reviewList", reviewList);
			req.getRequestDispatcher("/WEB-INF/views/admin/review.jsp").forward(req, resp);
		}
	}

}
