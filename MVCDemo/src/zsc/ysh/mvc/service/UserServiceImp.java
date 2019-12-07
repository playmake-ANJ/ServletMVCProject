package zsc.ysh.mvc.service;

import java.util.List;

import zsc.ysh.mvc.dao.FactoryDao;
import zsc.ysh.mvc.dao.UserDao;
import zsc.ysh.mvc.model.User;

/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��7��14�� ����1:23:18 
* ��˵�� 
*/
public class UserServiceImp implements UserService{
	
	UserDao userDao = FactoryDao.getUserDao();
	@Override
	public int insert(User user) {
		
		return userDao.insert(user);
	}

	@Override
	public User getOneDataById(int id) {
		
		return userDao.getOneDataById(id);
	}

	@Override
	public User getOneByNameAndPasswd(String userName, String passwd) {
		
		return userDao.getOneByNameAndPasswd(userName, passwd);
	}

	@Override
	public int deleteById(int id) {
		
		return userDao.deleteById(id);
	}

	@Override
	public int updateById(int id, User user) {
		
		return userDao.updateById(id, user);
	}

	@Override
	public List<User> getList() {
		
		return userDao.getList();
	}

	@Override
	public List<User> queryByData(String userName, String address, String phone) {
		
		return userDao.queryByData(userName, address, phone);
	}

	@Override
	public User getOneByName(String userName) {
		return userDao.getOneByName(userName);
	}

	@Override
	public long getCountByName(String name) {
		return userDao.getCountByName(name);
	}

}
