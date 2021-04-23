package dto;

import java.util.Date;

public class ReviewBoard {
	
	private int reviewNo;
	private int reviewSort;
	private int userNo;
	private String title;
	private String content;
	private Date createDate;
	private Date updateDate;
	private int views;
	
	@Override
	public String toString() {
		return "ReviewBoard [reviewNo=" + reviewNo + ", reviewSort=" + reviewSort + ", userNo=" + userNo + ", title="
				+ title + ", content=" + content + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", views=" + views + "]";
	}

	
	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getReviewSort() {
		return reviewSort;
	}

	public void setReviewSort(int reviewSort) {
		this.reviewSort = reviewSort;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
}
