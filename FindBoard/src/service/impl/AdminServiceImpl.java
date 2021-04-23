package service.impl;

import java.sql.Connection;
import java.util.List;

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
	public List<Product> getProdListByCateId(AdminPaging apaging, Product p) {
		Connection conn = JDBCTemplate.getConnection();
		return adminDao.selectProductByCateId(conn, apaging, p);
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

}
