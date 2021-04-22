package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminDao;
import dto.FindBoard;
import dto.Product;
import dto.Usertb;
import oracle.net.aso.p;
import util.AdminPaging;

public class AdminDaoImpl implements AdminDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Usertb> selectAll(Connection conn) {
		String sql = "";
		sql += "SELECT user_no, user_id, nick, email FROM usertb";
		sql += " ORDER BY user_no";
		
		List<Usertb> userList = new ArrayList<Usertb>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Usertb user = new Usertb();
				user.setUserNo(rs.getInt("user_no"));
				user.setUserId(rs.getString("user_id"));
				user.setNick(rs.getString("nick"));
				user.setEmail(rs.getString("email"));
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userList;
	}

	@Override
	public int selectCntAll(Connection conn) {
		String sql = "";
		sql += "SELECT count(*) cnt FROM board";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cnt;
	}

	@Override
	public List<Usertb> selectAll(Connection conn, AdminPaging apaging) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " 	SELECT rownum rnum, U.* FROM (";
		sql += " 		SELECT user_no, user_id, nick, email FROM usertb";
		sql += " 		ORDER BY user_no ";
		sql += "	) U";
		sql += " ) ";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Usertb> userList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, apaging.getStartNo());
			ps.setInt(2, apaging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Usertb user = new Usertb(); 
				
				user.setUserNo(rs.getInt("user_no"));
				user.setUserId(rs.getString("user_id"));
				user.setNick(rs.getString("nick"));
				user.setEmail(rs.getString("email"));
				
				userList.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userList;
	}

	@Override
	public int deleteUserByUserno(Connection conn, int userno) {
		String sql = "";
		sql += "DELETE FROM usertb";
		sql += " WHERE user_no = ?";
		
		int result = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userno);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	@Override
	public List<FindBoard> selectFindBoard(Connection conn, AdminPaging apaging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " 	SELECT rownum rnum, F.* FROM (";
		sql += "		SELECT find_no, user_no, title, content, views FROM findboard";
		sql += " 		ORDER BY find_no DESC";
		sql += "	) F";
		sql += " ) ";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<FindBoard> findList = new ArrayList<FindBoard>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, apaging.getStartNo());
			ps.setInt(2, apaging.getEndNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FindBoard findBoard = new FindBoard();
				
				findBoard.setFindNo(rs.getInt("find_no"));
				findBoard.setUserNo(rs.getInt("user_no"));
				findBoard.setTitle(rs.getString("title"));
				findBoard.setContent(rs.getString("content"));
				findBoard.setViews(rs.getInt("views"));
				
				findList.add(findBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return findList;
	}

	@Override
	public int deleteFindByFindno(Connection conn, int findno) {
		String sql = "";
		sql += "DELETE FROM findboard";
		sql += " WHERE find_no = ?";
		
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, findno);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	@Override
	public List<Product> selectProduct(Connection conn, AdminPaging apaging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, P.* FROM (";
		sql += "		SELECT product_id, category_id, product_name, price";
		sql += "		FROM product";
		sql += "		ORDER BY product_id";
		sql += "	) P";   
		sql += " ) WHERE rnum BETWEEN ? AND ?";   
			         
		List<Product> pList = new ArrayList<Product>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, apaging.getStartNo());
			ps.setInt(2, apaging.getEndNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				
				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				
				pList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return pList;
	}

	@Override
	public List<Product> selectProductByCateId(Connection conn, AdminPaging apaging, Product categoryId) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, P.* FROM (";
		sql += "		SELECT product_id, product_name, price FROM product";
		sql += " 		WHERE category_id = ?";
		sql += " 		ORDER BY product_id";
		sql += "	) P";
		sql += " ) WHERE rnum BETWEEN ? AND ?";
			   
		List<Product> pList = new ArrayList<Product>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, categoryId.getCategoryId());
			ps.setInt(2, apaging.getStartNo());
			ps.setInt(3, apaging.getEndNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				
				p.setProductId(rs.getInt("product_id"));
				p.setProductName(rs.getString("product_name"));
				p.setPrice(rs.getInt("price"));
				
				pList.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return pList;
	}
}
