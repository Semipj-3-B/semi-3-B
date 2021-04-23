package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Usertb;

public interface LoginService {

	public Usertb getParam(HttpServletRequest req);

	public boolean login(Usertb param);

	public Usertb loginUser(Usertb param);

}
