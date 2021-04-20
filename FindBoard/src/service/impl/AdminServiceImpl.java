package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminDao;
import dao.impl.AdminDaoImpl;
import dto.Usertb;
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
	public List<Usertb> getList(AdminPaging apaging) {
		return adminDao.selectAll(JDBCTemplate.getConnection(), apaging);
	}


}
