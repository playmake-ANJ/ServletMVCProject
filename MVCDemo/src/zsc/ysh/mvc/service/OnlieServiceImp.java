package zsc.ysh.mvc.service;

import java.util.List;

import zsc.ysh.mvc.dao.FactoryDao;
import zsc.ysh.mvc.dao.OnlieDao;
import zsc.ysh.mvc.model.Onlie;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月11日 下午1:31:51 
* 类说明 
*/
public class OnlieServiceImp implements OnlieService{
	OnlieDao onlieDao = FactoryDao.getOnlieDao();
	@Override
	public List<Onlie> getAllOnlie() {
		return onlieDao.getAllOnlie();
	}

	@Override
	public void insertOnlie(Onlie onlie) {
		onlieDao.insertOnlie(onlie);
	}

	@Override
	public void updateOnlie(Onlie onlie) {
		onlieDao.updateOnlie(onlie);
	}

	@Override
	public void deleteOnlie(String ssid) {
		onlieDao.deleteOnlie(ssid);
	}

	@Override
	public Onlie getOnlieBySsid(String ssid) {
		return onlieDao.getOnlieBySsid(ssid);
	}

}
