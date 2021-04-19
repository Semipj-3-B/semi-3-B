package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminDao;
import dao.impl.AdminDaoImpl;
import dto.Usertb;
import service.face.AdminService;
import util.Paging;

public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao = new AdminDaoImpl();
	
	@Override
	public List<Usertb> getList() {
		return adminDao.selectAll(JDBCTemplate.getConnection());
	}


}
