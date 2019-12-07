package zsc.ysh.mvc.dao;

import java.sql.Date;
import java.util.List;

import zsc.ysh.mvc.model.Onlie;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月11日 下午1:11:36 
* 类说明 
*/
public class OnlieDaoImp extends BaseDao<Onlie> implements OnlieDao{

	@Override
	public List<Onlie> getAllOnlie() {
		String sql = "SELECT * FROM `onlie`";
		return super.getList(sql);
	}

	@Override
	public void insertOnlie(Onlie onlie) {
		String sql = "INSERT INTO onlie VALUES(?,?,?,?,?)";
		super.uid(sql, onlie.getSsid(),onlie.getUserName(),onlie.getPage(),onlie.getIp(),new Date(onlie.getTime().getTime()));
	}

	@Override
	public void updateOnlie(Onlie onlie) {
		String sql = "UPDATE onlie set userName=?,page=?,ip=?,`time`=? where ssid=?";
		super.uid(sql, onlie.getUserName(),onlie.getPage(),onlie.getIp(),new Date(onlie.getTime().getTime()),onlie.getSsid());
	}

	@Override
	public void deleteOnlie(String ssid) {
		String sql = "DELETE FROM onlie WHERE ssid=?";
	    super.uid(sql, ssid);
	}

	@Override
	public Onlie getOnlieBySsid(String ssid) {
		String sql = "SELECT *FROM onlie WHERE ssid=?";
		return super.get(sql, ssid);
	}

}
