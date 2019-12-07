package zsc.ysh.mvc.service;

import java.util.List;

import zsc.ysh.mvc.model.Onlie;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月11日 下午1:31:10 
* 类说明 
*/
public interface OnlieService {
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
