package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dto.DiscoverBoard;
import dto.DiscoverComment;
import dto.DiscoverImg;
import util.Paging;

public interface DiscoverBoardDao {

	public List<DiscoverBoard> selectAll(Connection conn, Paging paging, Map<String, String> map);

	public int selectCntAll(Connection conn);

	public DiscoverBoard selectBoardByDiscoverno(Connection conn, DiscoverBoard discoverno);

	public int updateHit(Connection conn, DiscoverBoard discoverno);

	public String selectNickByUserNo(Connection conn, DiscoverBoard viewDiscoverBoard);

	public String selectEmailByUserNo(Connection conn, DiscoverBoard viewDiscoverBoard);

	public List<DiscoverImg> selectFile(Connection conn, DiscoverBoard viewDiscoverBoard);

	public int selectDiscoverno(Connection conn);

	public int insert(Connection conn, DiscoverBoard discoverBoard);

	public int insertImg(Connection conn, List<DiscoverImg> discoverImages);

	public int selectUserno(Connection conn, String userid);

	public int deleteFile(Connection conn, DiscoverBoard discoverboard);

	public int delete(Connection conn, DiscoverBoard discoverboard);

	public int update(Connection conn, DiscoverBoard discoverBoard);

	public List<DiscoverComment> selectComment(Connection conn, int discoverNo);

	public int insertComment(Connection conn, DiscoverComment param);

	public int updateComment(Connection conn, DiscoverComment param);

	public int deleteComment(Connection conn, DiscoverComment param);



}
