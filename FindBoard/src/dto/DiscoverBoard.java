package dto;

import java.util.Date;

public class DiscoverBoard {
	
	private int discoverNo;
	private int userNo;
	private String title;
	private Date createDate;
	private Date updateDate;
	private int views;
	private String petName;
	private String petKinds;
	private int petAge;
	private String loc;
	private String content;
	private int boardDiv;
	
	
	@Override
	public String toString() {
		return "DiscoverBoard [discoverNo=" + discoverNo + ", userNo=" + userNo + ", title=" + title + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", views=" + views + ", petName=" + petName
				+ ", petKinds=" + petKinds + ", petAge=" + petAge + ", loc=" + loc + ", content=" + content
				+ ", boardDiv=" + boardDiv + "]";
	}


	public int getDiscoverNo() {
		return discoverNo;
	}


	public void setDiscoverNo(int discoverNo) {
		this.discoverNo = discoverNo;
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


	public String getPetName() {
		return petName;
	}


	public void setPetName(String petName) {
		this.petName = petName;
	}


	public String getPetKinds() {
		return petKinds;
	}


	public void setPetKinds(String petKinds) {
		this.petKinds = petKinds;
	}


	public int getPetAge() {
		return petAge;
	}


	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}


	public String getLoc() {
		return loc;
	}


	public void setLoc(String loc) {
		this.loc = loc;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getBoardDiv() {
		return boardDiv;
	}


	public void setBoardDiv(int boardDiv) {
		this.boardDiv = boardDiv;
	}
	
	
	
	
	

}
