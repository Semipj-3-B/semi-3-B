package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Usertb;
import util.Paging;

public interface AdminService {

	/**
	 * 모든 회원의 목록과 정보를 가져온다.
	 * @return	모든 회원의 정보
	 */
	List<Usertb> getList();


}
