package zsc.ysh.mvc.service;

import java.util.List;

import zsc.ysh.mvc.model.User;

/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��7��14�� ����1:21:33 
* ��˵�� 
*/
public  interface UserService {
	/*
	 * ����һ������
	 */
	public int insert(User user);
	
	/*
	 * ����id��ѯһ������
	 */
	public User getOneDataById(int id);
	
	/*
	 * �����û����������ѯһ������
	 */
	public User getOneByNameAndPasswd(String userName,String passwd);
	
	/*
	 * �����û�����ѯһ������
	 */
	public User getOneByName(String userName);
	
	/*
	 * ����idɾ��һ������
	 */
	public int deleteById(int id);
	
	/*
	 * ����id�޸�����
	 */
	public int updateById(int id,User user);
	
	/*
	 * ��ȡ��������
	 */
	public List<User> getList();
	
	/*
	 * �����û���ģ����ѯ
	 */
	public List<User> queryByData(String userName,String address,String phone);
	
	/*
	 * �������ַ��ؼ�¼����
	 */
	public long getCountByName(String name); 


}
