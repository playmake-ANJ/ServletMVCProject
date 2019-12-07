package zsc.ysh.mvc.dao;

import java.util.List;

import zsc.ysh.mvc.model.User;

/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��7��14�� ����12:46:44 
* ��˵�� 
*/
public class UserDaoImp extends BaseDao<User> implements UserDao{
	
	/*
	 * ����һ������
	 */
	@Override
	public int insert(User user) {
		int row = 0;
		String sql = "INSERT INTO `user`(userName,passwd,address,phone,regDate) VALUES(?,?,?,?,?) ";
		row = super.uid(sql, user.getUserName(),user.getPasswd(),user.getAddress(),user.getPhone()
				,new java.sql.Date(user.getRegDate().getTime()) );
		return row;
	}
	
	
	/*
	 * ����id��ѯһ������
	 */
	@Override
	public User getOneDataById(int id) {
		User user = null;
		String sql = "SELECT *FROM `user` WHERE userId=?";
		user = super.get(sql, id);
		return user;
	}
    
	/*
	 * �����û����������ѯһ������
	 */
	@Override
	public User getOneByNameAndPasswd(String userName, String passwd) {
		User user = null;
		String sql = "SELECT *FROM `user` WHERE userName=? and passwd=?";
		user = super.get(sql, userName,passwd);
		return user;
	}
    
	/*
	 * ����idɾ��һ������
	 */
	@Override
	public int deleteById(int id) {
		int row = 0;
		String sql = "DELETE FROM `user` WHERE userId=?";
		row = super.uid(sql, id);
		return row;
	}
    
	/*
	 * ����id�޸�����
	 */
	@Override
	public int updateById(int id, User user) {
		int row = 0;
		String sql = "UPDATE `user` set userName=?,passwd=?,address=?,phone=? WHERE userId=?";
		row = super.uid(sql, user.getUserName(),user.getPasswd(),user.getAddress(),user.getPhone(),id);
		return row;
	}

	/*
	 * ��ȡ��������
	 */
	@Override
	public List<User> getList() {
		List<User> list = null;
		String sql = "select *from user";
		list = super.getList(sql);
		return list;
	}

	/*
	 * �����û���ģ����ѯ
	 */
	@Override
	public List<User> queryByData(String userName, String address, String phone) {
		String sql = "select *from user where 1=1";
		List<User> list = null;
		if(userName != null && !"".equals(userName)) {
			sql = sql+ " and userName like '%"+userName+"%'";
		}
		if(address != null && !"".equals(address)) {
			sql = sql+ " and address like '%"+address+"%'";
		}
		if(phone != null && !"".equals(phone)) {
			sql = sql+ " and phone like '%"+ phone +"%'";
		}
		list = super.getList(sql);
		return list;
	}


	@Override
	public User getOneByName(String userName) {
		User user = null;
		String sql = "SELECT *FROM `user` WHERE userName=?";
		user = super.get(sql, userName);
		return user;
	}


	@Override
	public long getCountByName(String name) {
		String sql = "SELECT COUNT(userId) FROM user WHERE `userName`=?";
		long count = (long)super.getCount(sql, name);
		return count;
	}

}
