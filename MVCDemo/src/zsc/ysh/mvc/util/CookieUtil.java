package zsc.ysh.mvc.util;

import java.security.MessageDigest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年7月16日 下午12:10:13 
* 类说明 
*/
public class CookieUtil {
	
	//创建密钥
	public final static String KEY = "cookie@key!&&ysh";
	
	//创建Cookie
	public static void creatCookie(String userName,HttpServletRequest req, HttpServletResponse resp,int seconed) {
		Cookie userCookie = new Cookie("userKey",userName);
		Cookie userSsidCookie = new Cookie("ssid",md5(userName));
		userCookie.setMaxAge(seconed);
		userSsidCookie.setMaxAge(seconed);
		resp.addCookie(userCookie);
		resp.addCookie(userSsidCookie);
	}
	
	//加密方法:md5,把明文的字符串加密成密文
	
	public static String md5(String str) {
		str = str==null ?"":str+KEY ;
		char[] md5Dis = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] secStr = str.getBytes();
			//把明文放到md中
			md.update(secStr);
			byte[] sstr = md.digest();//这个是真正的加密
			
			//将字典结合加密
			int len = sstr.length;
			char[] disStr = new char[len*2];
			int k = 0; //用于记数
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
