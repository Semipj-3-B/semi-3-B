package dto;

import java.util.Date;

public class ReviewComment {
	
	private int commentNo;
	private int reviewNo;
	private int userNo;
	private String nick;
	private String commentText;
	private Date commentDate;
	
	//userimg JOIN
	private String storedName;
	
	//total comment count
	private int commentCnt;
	
	
	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	@Override
	public String toString() {
		return "ReviewComment [commentNo=" + commentNo + ", reviewNo=" + reviewNo + ", userNo=" + userNo + ", nick="
				+ nick + ", commentText=" + commentText + ", commentDate=" + commentDate + ", storedName=" + storedName
				+ ", commentCnt=" + commentCnt + "]";
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

}
