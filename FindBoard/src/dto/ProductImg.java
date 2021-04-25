package dto;

public class ProductImg {
	
	private int productId;
	private int imageNo;
	private String originImg;
	private String storedImg;
	
	
	@Override
	public String toString() {
		return "ProductImg [productId=" + productId + ", imageNo=" + imageNo + ", originImg=" + originImg
				+ ", storedImg=" + storedImg + "]";
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getImageNo() {
		return imageNo;
	}


	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}


	public String getOriginImg() {
		return originImg;
	}


	public void setOriginImg(String originImg) {
		this.originImg = originImg;
	}


	public String getStoredImg() {
		return storedImg;
	}


	public void setStoredImg(String storedImg) {
		this.storedImg = storedImg;
	}
	
	
	
	

}
