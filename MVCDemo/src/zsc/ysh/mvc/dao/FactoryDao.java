package zsc.ysh.mvc.dao;
/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��7��14�� ����1:19:18 
* ��˵�� 
*/
public class FactoryDao {
	
	public static UserDao getUserDao() {
		return new UserDaoImp();
	}
	public static OnlieDao getOnlieDao() {
		return new OnlieDaoImp();
	}
}
