package dao.face;

import java.sql.Connection;

import dto.Usertb;

public interface SignUpDao {

	public int insertUser(Connection conn, Usertb user);

	public int selectId(Connection conn, String id);

	public int selectNick(Connection conn, String nick);

}
