package zsc.ysh.mvc.dao;
/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年7月14日 下午1:19:18 
* 类说明 
*/
public class FactoryDao {
	
	public static UserDao getUserDao() {
		return new UserDaoImp();
	}
	public static OnlieDao getOnlieDao() {
		return new OnlieDaoImp();
	}
}
