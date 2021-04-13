package dto;

public class FindImg {
	
	private int imgNum;
	private int findNo;
	private String originImg;
	private String storedImg;
	
	
	@Override
	public String toString() {
		return "FimdImg [imgNum=" + imgNum + ", findNo=" + findNo + ", originImg=" + originImg + ", sotredImg="
				+ storedImg + "]";
	}
	
	
	public int getImgNum() {
		return imgNum;
	}
	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}
	public int getFindNo() {
		return findNo;
	}
	public void setFindNo(int findNo) {
		this.findNo = findNo;
	}
	public String getOriginImg() {
		return originImg;
	}
	public void setOriginImg(String originImg) {
		this.originImg = originImg;
	}
	public String getSotredImg() {
		return storedImg;
	}
	public void setSotredImg(String sotredImg) {
		this.storedImg = sotredImg;
	}
	
	

}
