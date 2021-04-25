package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ProductDao;

import dto.Product;
import dto.ProductImg;

public class ProductDaoImpl implements ProductDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public Product selectProdByProdId(Connection conn, Product productid) {
		String sql = "";
		sql += "SELECT * FROM product";
		sql += " WHERE product_id = ?";
		
		Product product = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, productid.getProductId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				product = new Product();
				product.setProductId( rs.getInt("product_id") );
				product.setCategoryId( rs.getInt("category_id") );
				product.setProductName( rs.getString("product_name") );
				product.setPrice( rs.getInt("price") );
				product.setContent( rs.getString("content") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return product;
	}

	@Override
	public List<ProductImg> selectImg(Connection connection, Product viewProduct) {
		
		//SQL
		String sql = "";
		sql += "SELECT * FROM product_img";
		sql += "WHERE product_id = ?";
		
		List<ProductImg> productImg = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql);
		
			ps.setInt(1, viewProduct.getProductId() );
		
			rs = ps.executeQuery();
			
			while(rs.next() ) {
				ProductImg pi = new ProductImg();
				
				pi.setProductId( rs.getInt("product_Id") );
				pi.setImageNo( rs.getInt("image_No") );
				pi.setOriginImg( rs.getString("origin_Img") );
				pi.setStoredImg( rs.getString("stored_Img") );
				
				//결과값 저장
				productImg.add(pi);
				
			}
			
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return null;
	}

	



}
