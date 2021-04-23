package dto;

public class ReviewImgFile {
	
	private int imageNo;
	private int reviewNo;
	private String originImg;
	private String storedImg;
	
	@Override
	public String toString() {
		return "ReviewImgFile [imageNo=" + imageNo + ", reviewNo=" + reviewNo + ", originImg=" + originImg
				+ ", storedImg=" + storedImg + "]";
	}

	public int getImageNo() {
		return imageNo;
	}

	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
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
