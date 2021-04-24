package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Usertb;

public interface SignUpService {

	public String sendEmail(String email);

	public Usertb getParam(HttpServletRequest req);

	public int signUpUser(Usertb user);

	public int signUpIDCheck(String id);

	public int signUpNickCheck(String nick);

}
