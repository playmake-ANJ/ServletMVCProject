package zsc.ysh.mvc.service;

import java.util.List;

import zsc.ysh.mvc.dao.FactoryDao;
import zsc.ysh.mvc.dao.OnlieDao;
import zsc.ysh.mvc.model.Onlie;

/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��8��11�� ����1:31:51 
* ��˵�� 
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
