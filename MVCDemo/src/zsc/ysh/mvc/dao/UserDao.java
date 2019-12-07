package zsc.ysh.mvc.dao;

import java.util.List;

import zsc.ysh.mvc.model.User;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年7月14日 下午12:31:52 
* 类说明 
*/
public interface UserDao {
	/*
	 * 插入一条数据
	 */
	public int insert(User user);
	
	/*
	 * 根据id查询一条数据
	 */
	public User getOneDataById(int id);
	
	/*
	 * 根据用户名和密码查询一条数据
	 */
	public User getOneByNameAndPasswd(String userName,String passwd);
	
	/*
	 * 根据用户名查询一条数据
	 */
	public User getOneByName(String userName);
	
	/*
	 * 根据id删除一条数据
	 */
	public int deleteById(int id);
	
	/*
	 * 根据id修改数据
	 */
	public int updateById(int id,User user);
	
	/*
	 * 获取所以数据
	 */
	public List<User> getList();
	
	/*
	 * 根据用户名模糊查询
	 */
	public List<User> queryByData(String userName,String address,String phone);
	
	/*
	 * 根据名字返回记录条数
	 */
	public long getCountByName(String name); 

}
