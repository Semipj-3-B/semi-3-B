package dto;

import java.util.Date;

public class UserAddress {
	private int userNo;
	private String userPostcode;
	private String userRoadAddress;
	private String userJibunAddress;
	private String userDetailAddress;
	private String userExtraAddress;
	private Date writeDate;
	private Date updateDate;
	@Override
	public String toString() {
		return "UserAddress [userNo=" + userNo + ", userPostcode=" + userPostcode + ", userRoadAddress="
				+ userRoadAddress + ", userJibunAddress=" + userJibunAddress + ", userDetailAddress="
				+ userDetailAddress + ", userExtraAddress=" + userExtraAddress + ", writeDate=" + writeDate
				+ ", updateDate=" + updateDate + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserPostcode() {
		return userPostcode;
	}
	public void setUserPostcode(String userPostcode) {
		this.userPostcode = userPostcode;
	}
	public String getUserRoadAddress() {
		return userRoadAddress;
	}
	public void setUserRoadAddress(String userRoadAddress) {
		this.userRoadAddress = userRoadAddress;
	}
	public String getUserJibunAddress() {
		return userJibunAddress;
	}
	public void setUserJibunAddress(String userJibunAddress) {
		this.userJibunAddress = userJibunAddress;
	}
	public String getUserDetailAddress() {
		return userDetailAddress;
	}
	public void setUserDetailAddress(String userDetailAddress) {
		this.userDetailAddress = userDetailAddress;
	}
	public String getUserExtraAddress() {
		return userExtraAddress;
	}
	public void setUserExtraAddress(String userExtraAddress) {
		this.userExtraAddress = userExtraAddress;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
