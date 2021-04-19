package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Usertb;
import util.Paging;

public interface AdminDao {

	/**
	 * Usertb를 페이징 처리하여 전체 조회한다.
	 * @param conn		DB 연결 객체
	 * @return		Usertb 테이블 전체 조회 데이터
	 */
	List<Usertb> selectAll(Connection conn);
	
	



}
