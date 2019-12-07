package zsc.ysh.mvc.dao;
/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月11日 下午12:53:22 
* 类说明 
*/

import java.util.List;

import zsc.ysh.mvc.model.Onlie;

public interface OnlieDao {
	/*
	 * @取出所有在线访问者的信息
	 */
	public List<Onlie> getAllOnlie();
	
	/*
	 * @插入一条访问者信息
	 */
	public void insertOnlie(Onlie onlie);
	
	/*
	 * @更新保存的Online信息
	 */
	public void updateOnlie(Onlie onlie);
	
	/*
	 * @删除
	 */
	public void deleteOnlie(String ssid);
	
	/*
	 * @根据Ssid返回一条数据
	 */
	public Onlie getOnlieBySsid(String ssid);
}
