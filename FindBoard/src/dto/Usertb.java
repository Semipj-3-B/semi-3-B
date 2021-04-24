package dto;

public class Usertb {
	
	private int userNo;
	private String userId;
	private String userPw;
	private String nick;
	private int year;
	private int month;
	private int day;
	private String name;
	private String gender;
	private String email;
	private String phone;
	private int postnum;
	private String addr;
	private String addrDetail;
	@Override
	public String toString() {
		return "Usertb [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", nick=" + nick + ", year="
				+ year + ", month=" + month + ", day=" + day + ", name=" + name + ", gender=" + gender + ", email="
				+ email + ", phone=" + phone + ", postnum=" + postnum + ", addr=" + addr + ", addrDetail=" + addrDetail
				+ "]";
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPostnum() {
		return postnum;
	}
	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	
	
}