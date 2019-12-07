package zsc.ysh.mvc.util;

import java.security.MessageDigest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��7��16�� ����12:10:13 
* ��˵�� 
*/
public class CookieUtil {
	
	//������Կ
	public final static String KEY = "cookie@key!&&ysh";
	
	//����Cookie
	public static void creatCookie(String userName,HttpServletRequest req, HttpServletResponse resp,int seconed) {
		Cookie userCookie = new Cookie("userKey",userName);
		Cookie userSsidCookie = new Cookie("ssid",md5(userName));
		userCookie.setMaxAge(seconed);
		userSsidCookie.setMaxAge(seconed);
		resp.addCookie(userCookie);
		resp.addCookie(userSsidCookie);
	}
	
	//���ܷ���:md5,�����ĵ��ַ������ܳ�����
	
	public static String md5(String str) {
		str = str==null ?"":str+KEY ;
		char[] md5Dis = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] secStr = str.getBytes();
			//�����ķŵ�md��
			md.update(secStr);
			byte[] sstr = md.digest();//����������ļ���
			
			//���ֵ��ϼ���
			int len = sstr.length;
			char[] disStr = new char[len*2];
			int k = 0; //���ڼ���
			for(int i = 0;i < len;i++) {
				byte b = sstr[i];
				disStr[k++] = md5Dis[b >>> 4 & 0xf];
				disStr[k++] = md5Dis[b & 0xf];
			}
			return new String(disStr);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
