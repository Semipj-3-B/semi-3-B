package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import common.JDBCTemplate;
import dao.face.AdminDao;
import dao.impl.AdminDaoImpl;
import dto.FindBoard;
import dto.Product;
import dto.Usertb;
import oracle.net.aso.p;
import service.face.AdminService;
import util.AdminPaging;

public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao = new AdminDaoImpl();
	
	@Override
	public List<Usertb> getList() {
		return adminDao.selectAll(JDBCTemplate.getConnection());
	}

	@Override
	public AdminPaging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		
		int totalCount = adminDao.selectCntAll(JDBCTemplate.getConnection());

		//Paging객체 생성
		AdminPaging paging = new AdminPaging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public List<Usertb> getUserList(AdminPaging apaging) {
		return adminDao.selectAll(JDBCTemplate.getConnection(), apaging);
	}

	@Override
	public void withdraw(HttpServletRequest req) {
		String usernoString = req.getParameter("userno");
		int userno = 0;
		
		if(usernoString != null && !"".equals(usernoString)) {
			userno = Integer.parseInt(usernoString);
		}
		
		Connection conn = JDBCTemplate.getConnection();
		int result = adminDao.deleteUserByUserno(conn, userno);
		if(result > 0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
	}

	@Override
	public List<FindBoard> getFindList(AdminPaging apaging) {
		
		return adminDao.selectFindBoard(JDBCTemplate.getConnection(), apaging);
	}

	@Override
	public void deleteFind(HttpServletRequest req) {
		String findnoString = req.getParameter("findno");
		int findno = 0;
		if(findnoString != null && !"".equals(findnoString)) {
			findno = Integer.parseInt(findnoString);
		}
		
		Connection conn = JDBCTemplate.getConnection();
		int result = adminDao.deleteFindByFindno(conn, findno);
		if(result > 0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
	}

	@Override
	public List<Product> getProductList(AdminPaging apaging) {
		
		return adminDao.selectProduct(JDBCTemplate.getConnection(), apaging);
	}

	@Override
	public List<Product> getProdListByCateId(int categoryId) {
		return adminDao.selectProductByCateId(JDBCTemplate.getConnection(), categoryId);
	}

	@Override
	public void deleteProduct(HttpServletRequest req) {
		String param = req.getParameter("prodId");
		System.out.println("param: " + param);
		
		Product product = null;
		if(param != null && !"".equals(param)) {
			product = new Product();
			product.setProductId(Integer.parseInt(param));
		}
		
		Connection conn = JDBCTemplate.getConnection();
		int result = adminDao.deleteProdByCateId(conn, product);
		if(result > 0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
	}

	@Override
	public boolean isAjaxReq(HttpServletRequest req) {
		boolean flag = false;
		String petparam = req.getParameter("pet");
		String locparam = req.getParameter("loc");
		
		if(petparam != null && !"".equals(petparam)) flag = true;
		if(locparam != null && !"".equals(locparam)) flag = true;
		
		return flag;
	}

	@Override
	public List<FindBoard> getFindListByMap(Map<String, String> map) {
		Connection conn = JDBCTemplate.getConnection();
		List<FindBoard> fList = null;
		
		String pet = map.get("pet"); //val 또는 null 1
		String loc = map.get("loc"); //val 또는 null 3
		
		final String[] pets = {"dog", "cat", "etc"};
		final String[] locs = {"서울특별시", "경기도", "강원도", "충청북도", "충청남도"
				, "경상북도", "경상남도", "전라북도", "전라남도", "대전광역시"
				, "광주광역시", "인천광역시", "부산광역시", "대구광역시"
				, "울산광역시", "세종시", "제주시"};
		
		//pet, loc 모두 값이 존재하는 경우
		if(pet != null && !"".equals(pet) && loc != null && !"".equals(loc) ) {
			int value1 = Integer.parseInt(pet) - 1;
			int value2 = Integer.parseInt(loc) - 1;
			
			for(int i = 0; i < pets.length; i++) {
				if(i == value1) pet = pets[i];
			}
			for(int i = 0; i < locs.length; i++) {
				if(i == value2) loc = locs[i];
			}
			
			map.put("pet", pet);
			map.put("loc", loc);
			fList = adminDao.selectFindByMap(conn, map);
			
		} else if (pet != null && !"".equals(pet)) {//pet 값만 존재하는 경우
			if(loc == null || "".equals(loc)) {
				int value1 = Integer.parseInt(pet) - 1;
				for(int i = 0; i < pets.length; i++) {
					if(i == value1) pet = pets[i];
				}
				fList = adminDao.selectFindByPet(conn, pet);
			}
			
		} else {
			int value2 = Integer.parseInt(loc) - 1;
			for(int i = 0; i < locs.length; i++) { //loc 값만 존재하는 경우
				if(i == value2) loc = locs[i];
			}
			fList = adminDao.selectFindByLoc(conn, loc);
		}
	
		return fList;
	}

}
