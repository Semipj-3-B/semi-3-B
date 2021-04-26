package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Admin;
import dto.Usertb;

public interface LoginService {

	public Usertb getParam(HttpServletRequest req);

	public boolean login(Usertb param);

	public Usertb loginUser(Usertb param);

	/**
	 * id, pw를 관리자 객체로 저장한다.
	 * @param req	관리자 계정 요청 파라미터
	 * @return		관리자 객체
	 */
	public Admin getParameter(HttpServletRequest req);

	public boolean login(Admin adminParam);

	public Admin loginAdmin(Admin adminParam);


}
