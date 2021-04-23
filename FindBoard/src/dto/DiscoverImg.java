package dto;

public class DiscoverImg {
	
	private int imageNo;
	private int discoverNo;
	private String originImg;
	private String storedImg;
	
	
	@Override
	public String toString() {
		return "DiscoverImg [imageNo=" + imageNo + ", discoverNo=" + discoverNo + ", originImg=" + originImg
				+ ", storedImg=" + storedImg + "]";
	}


	public int getImgNum() {
		return imageNo;
	}


	public void setImgNum(int imgNum) {
		this.imageNo = imgNum;
	}


	public int getDiscoverNo() {
		return discoverNo;
	}


	public void setDiscoverNo(int discoverNo) {
		this.discoverNo = discoverNo;
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
