package zsc.ysh.mvc.service;
/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��7��14�� ����1:32:28 
* ��˵�� 
*/
public class FactoryService {
	
	public static UserService getUserService() {
		return new UserServiceImp();
	}
	public static OnlieService getOnlieService() {
		return new OnlieServiceImp();
	}
}
