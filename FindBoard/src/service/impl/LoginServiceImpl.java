package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.LoginDao;
import dao.impl.LoginDaoImpl;
import dto.Usertb;
import service.face.LoginService;

public class LoginServiceImpl implements LoginService {
	
	private Connection conn = null;
	private LoginDao loginDao = new LoginDaoImpl();
	
	@Override
	public Usertb getParam(HttpServletRequest req) {

	Usertb user = new Usertb();
	
	user.setUserId(req.getParameter("id"));
	user.setUserPw(req.getParameter("pw"));
	
		return user;
	}
	
	@Override
	public boolean login(Usertb param) {

		conn = JDBCTemplate.getConnection();
		
		int res = loginDao.selectCntByUserId(conn, param);
		
		if(res > 0) return true;
		else return false;
		
	}
	
	@Override
	public Usertb loginUser(Usertb param) {

		conn = JDBCTemplate.getConnection();
		
		Usertb res = loginDao.selectUser(conn, param);
		
		return res;
	}
}
