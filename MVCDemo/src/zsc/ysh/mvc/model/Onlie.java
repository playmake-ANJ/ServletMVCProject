package zsc.ysh.mvc.model;
/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��8��11�� ����12:50:21 
* ��˵�� :���ڼ�¼����������JAVA bean
*/

import java.util.Date;

public class Onlie {
	private String ssid;
	private String userName;
	private String page;
	private String ip;
	private Date time;
	public Onlie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Onlie(String ssid, String userName, String page, String ip, Date time) {
		super();
		this.ssid = ssid;
		this.userName = userName;
		this.page = page;
		this.ip = ip;
		this.time = time;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Onlie [ssid=" + ssid + ", userName=" + userName + ", page=" + page + ", ip=" + ip + ", time=" + time
				+ "]";
	}
	
}
