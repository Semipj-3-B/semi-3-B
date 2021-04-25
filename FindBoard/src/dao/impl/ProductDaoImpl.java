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

	@Override
	public int selectproductId(Connection conn) {
		String sql = "";
		sql += "SELECT product_seq.nextval FROM dual";
		
		int prodId = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				prodId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return prodId;
	}


	@Override
	public int insert(Connection conn, Product product) {
		
		String sql = "";
		sql += "INSERT INTO product (product_id, category_id, product_name, price, content)";
		sql += " VALUES (?, ?, ?, ?, ?)";
		
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getProductId());
			ps.setInt(2, product.getCategoryId());
			ps.setString(3, product.getProductName());
			ps.setInt(4, product.getPrice());
			ps.setString(5, product.getContent());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println("DAO의 result 값 = " + result);
		return result;
	}


	@Override
	public int insertImg(Connection conn, List<ProductImg> productImgs) {
		String sql = "";
		sql += "INSERT INTO product_img (image_no, product_id, origin_img, stored_img)";
		sql += " VALUES (product_img_seq.nextval, ?, ?, ?)";

		int result = -1;
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < productImgs.size(); i++) {
				ps.setInt(1, productImgs.get(i).getProductId());
				ps.setString(2, productImgs.get(i).getOriginImg());
				ps.setString(3, productImgs.get(i).getStoredImg());
			
				res += ps.executeUpdate();
			}//for() END
			
			result = res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	



}
