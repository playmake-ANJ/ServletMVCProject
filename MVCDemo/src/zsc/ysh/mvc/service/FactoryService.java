package zsc.ysh.mvc.service;
/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年7月14日 下午1:32:28 
* 类说明 
*/
public class FactoryService {
	
	public static UserService getUserService() {
		return new UserServiceImp();
	}
	public static OnlieService getOnlieService() {
		return new OnlieServiceImp();
	}
}
