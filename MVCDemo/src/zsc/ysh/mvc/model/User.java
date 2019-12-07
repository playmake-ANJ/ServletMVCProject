package zsc.ysh.mvc.model;

import java.util.Date;

public class User {
	private int userId;
	private String userName;
	private String passwd;
	private String address;
	private String phone;
	private Date regDate;
	public User(int userId, String userName, String passwd, String address, String phone, Date regDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passwd = passwd;
		this.address = address;
		this.phone = phone;
		this.regDate = regDate;
	}
	public User() {
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "User [id=" + userId + ", userName=" + userName + ", passwd=" + passwd + ", address=" + address + ", phone="
				+ phone + ", regDate=" + regDate + "]";
	}
	
	

}
