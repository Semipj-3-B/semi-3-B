package dto;

import java.util.Date;

// 리뷰 전체 리스트 정보 DTO

public class ReviewUserJoin {
	
	private int reviewNo;
	private String reviewSortDetail;
	private String title;
	private String userId;
	private Date createDate;
	private int views;
	
	@Override
	public String toString() {
		return "ReviewUserJoin [reviewNo=" + reviewNo + ", reviewSortDetail=" + reviewSortDetail + ", title=" + title
				+ ", userId=" + userId + ", createDate=" + createDate + ", views=" + views + "]";
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewSortDetail() {
		return reviewSortDetail;
	}

	public void setReviewSortDetail(String reviewSortDetail) {
		this.reviewSortDetail = reviewSortDetail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

}
