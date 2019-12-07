package zsc.ysh.mvc.service;

import java.util.List;

import zsc.ysh.mvc.model.Onlie;

/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��8��11�� ����1:31:10 
* ��˵�� 
*/
public interface OnlieService {
	/*
	 * @ȡ���������߷����ߵ���Ϣ
	 */
	public List<Onlie> getAllOnlie();
	
	/*
	 * @����һ����������Ϣ
	 */
	public void insertOnlie(Onlie onlie);
	
	/*
	 * @���±����Online��Ϣ
	 */
	public void updateOnlie(Onlie onlie);
	
	/*
	 * @ɾ��
	 */
	public void deleteOnlie(String ssid);
	
	/*
	 * @����Ssid����һ������
	 */
	public Onlie getOnlieBySsid(String ssid);
}
