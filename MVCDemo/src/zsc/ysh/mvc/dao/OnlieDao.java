package zsc.ysh.mvc.dao;
/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��8��11�� ����12:53:22 
* ��˵�� 
*/

import java.util.List;

import zsc.ysh.mvc.model.Onlie;

public interface OnlieDao {
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
