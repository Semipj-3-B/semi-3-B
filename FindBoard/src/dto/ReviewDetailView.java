package dto;

import java.util.Date;

// 리뷰 상세보기 페이지 정보 DTO

public class ReviewDetailView {
	
	private String reviewSortDetail;
	private String title;
	private String nick;
	private Date createDate;
	private String content;
	
	@Override
	public String toString() {
		return "ReviewDetailView [reviewSortDetail=" + reviewSortDetail + ", title=" + title
				+ ", nick=" + nick + ", createDate=" + createDate + ", content=" + content + "]";
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
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
