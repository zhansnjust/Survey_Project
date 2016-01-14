package njust.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import njust.dao.BaseDao;
import njust.model.Log;
import njust.service.LogService;

@Service("logService")
public class LogServiceImp extends BaseServiceImp<Log> implements LogService {
	@Resource(name="logDao")
	@Override
	public void setDao(BaseDao<Log> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

}
