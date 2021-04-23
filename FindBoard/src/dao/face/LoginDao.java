package dao.face;

import java.sql.Connection;

import dto.Usertb;

public interface LoginDao {

	public int selectCntByUserId(Connection conn, Usertb param);

	public Usertb selectUser(Connection conn, Usertb param);

}
