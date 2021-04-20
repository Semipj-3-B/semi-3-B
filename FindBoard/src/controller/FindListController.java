package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FindBoard;
import dto.FindImg;
import service.face.FindBoardService;
import service.impl.FindBoardServiceImpl;
import util.Paging;


@WebServlet("/find/list")
public class FindListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FindBoardService findboardService = new FindBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("findListController - GET");
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = findboardService.getPaging(req);
		System.out.println("findListController - " + paging);
		
		
		// ------ 해쉬 맵 ------------------
//		List<Map<String, Object>> list = new ArrayList<>();
//		Map<String, Object> map = new HashMap<>();
//		while(rs.next) {
//		map.put("findboard", new FindBoard());
//		map.put("findimg", new FindImg());
//		
//		list.add(map);
//		}
//		
//		return list;	
//		
//		
//		
//		((FindBoard)list.get(0).get("findboard")).getFindNo()
//		((FindBoard)list.get(0).get("findboard")).getTitle()
//		
//		((FindImg)list.get(0).get("findimg")).getStoredImg()
//		
		
		// ---------------------------------------------
		
		
		// ----------- DTO 객체 생성
		
		// 원본 손상위험
//		class FindBoard {
//			
//			private int findno;
//			
//			private String storedimg;
//			
//		}
		
		//원본 손상 없이 1대1
//		class FindBoard {
//			
//			private int findno;
//			
//			private FindImg findImg;
//			
//		}
			//현재 상품 정보의 썸네일 작성에는 이방법이 제일 편함

		//원본 손상 없이 1대 다수
//		class FindBoard {
//			
//			private int findno;
//			
//			private List<FindImg> findImgList = new ArrayList<>();
//			
//		}
		
		
		

		//게시글 전체 조회
//		List<FindBoard> findList = findboardService.getList();
		
		//페이징을 적용한 게시글 전체 조회
		List<FindBoard> findboardList = findboardService.getList(paging);
		//원본 손상 없이 1대1
		class FindBoard {
			
			private int findno;
			
			private FindImg findImg;
			
		}

		//페이징 객체를 MODEL값으로 전달
		req.setAttribute("paging", paging);
		
//		//조회결과 MODEL값 전달
		req.setAttribute("findList", findboardList);
		
		System.out.println("findList list컨트롤러 = " + findboardList);
//		
		
		// ------ 이미지 정보 보내기 ---------------
		
		
//		FindBoard findboardlistImg = findboardService.viewMainFile(findboardList);
		
		//첨부파일 정보 VIEW에 전달
//		List<FindImg> findImg = findboardService.viewMainFile(findboardList);
//		req.setAttribute("findImg", findImg);
//		
//		System.out.println("findImg"+findImg);
		
		
		//-------------------------------------------------
		
		
//		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/find/list.jsp").forward(req, resp);		
				
		
	}
}
