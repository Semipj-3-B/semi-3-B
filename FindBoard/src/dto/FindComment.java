package dto;

import java.util.Date;

public class FindComment {
	private int commentNo;
	private int findNo;
	private int userNo;
	private String commentText;
	private Date commentDate;
	private Date commentUpdate;
	
	@Override
	public String toString() {
		return "FindComment [commentNo=" + commentNo + ", findNo=" + findNo + ", userNo=" + userNo + ", commentText="
				+ commentText + ", commentDate=" + commentDate + "]";
	}
	
	//getter, setter
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getFindNo() {
		return findNo;
	}
	public void setFindNo(int findNo) {
		this.findNo = findNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
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
	public Date getCommentUpdate() {
		return commentUpdate;
	}
	public void setCommentUpdate(Date commentUpdate) {
		this.commentUpdate = commentUpdate;
	}
	
}
