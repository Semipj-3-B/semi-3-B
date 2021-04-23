package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.ProductDao;
import dto.Product;

public class ProductDaoImpl implements ProductDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public Product selectProdByProdId(Connection conn, int productId) {
		String sql = "";
		sql += "SELECT product_id, category_id, product_name, price FROM product";
		sql += " WHERE product_id = ?";
		
		Product product = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return product;
	}

}
