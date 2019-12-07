package zsc.ysh.mvc.listener;

import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import zsc.ysh.mvc.model.Onlie;
import zsc.ysh.mvc.service.FactoryService;
import zsc.ysh.mvc.service.OnlieService;

/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��8��11�� ����1:40:32 
* ��˵�� 
*/
/*����˼·��
 * 1�����������ʱ����¼�����ߵ���Ϣ���жϷ����ߣ������ݿ������������û�ʱ���������ķ���ʱ�䣬���û�м�¼����������
 * 2����¼�����û����ʵ�ʱ�䣬�������5����ô���κβ�������ʾ�û����ߣ�ɾ�����ݱ�ļ�¼
 * 3�������ߵ�¼�ɹ��󣬽����ݱ��е�UserName����·�˸�Ϊ�������û���
 */
@WebListener
public class OnlineRequestListener implements ServletRequestListener{

	private OnlieService onlieService = FactoryService.getOnlieService();
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		//��ȡsession����
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		HttpSession session = req.getSession();
		//��ȡsessionID
		String ssid = session.getId();
		//��ȡ�û���
		String userName = (String)session.getAttribute("user");
		userName = (userName == null? "·�˼�" : userName);
		//��ȡ�û����ʵ�ҳ���ַ
		String page = req.getRequestURI();
		//��ȡIp��ַ
		String ip = req.getRemoteAddr();
		Onlie onlie = onlieService.getOnlieBySsid(ssid);
		if(onlie == null) {
			//��������
			onlie = new Onlie(ssid,userName,page,ip,new Date());
			onlieService.insertOnlie(onlie);
		}else if(onlie != null){
			//��������
			onlie.setTime(new Date());
			onlie.setPage(page);
			onlieService.updateOnlie(onlie);
		}
		
	}

}
