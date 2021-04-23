package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.LoginDao;
import dao.impl.LoginDaoImpl;
import dto.Usertb;
import service.face.LoginService;
import util.HashNMacUtil;

public class LoginServiceImpl implements LoginService {
	
	private Connection conn = null;
	private LoginDao loginDao = new LoginDaoImpl();
	
	@Override
	public Usertb getParam(HttpServletRequest req) {

	Usertb user = new Usertb();
	
	//id, 비밀번호 파라미터값 저장
	user.setUserId(req.getParameter("id"));
	try {
		//비밀번호 Sha256 해쉬코드 암호화 처리
		user.setUserPw(HashNMacUtil.EncBySha256(req.getParameter("pw")));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		return user;
	}
	
	@Override
	public boolean login(Usertb param) {

		conn = JDBCTemplate.getConnection();
		
		//유저의 id, 비밀번호가 일치하는지 조회
		int res = loginDao.selectCntByUserId(conn, param);
		
		if(res > 0) return true;
		else return false;
		
	}
	
	@Override
	public Usertb loginUser(Usertb param) {

		conn = JDBCTemplate.getConnection();
		
		//유저의 정보조회
		Usertb res = loginDao.selectUser(conn, param);
		
		return res;
	}
}
