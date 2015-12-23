package njust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import njust.dao.BaseDao;
import njust.model.User;
import njust.service.UserService;
import njust.utils.ValidateUtil;

@Service("userService")
public class UserServiceImp extends BaseServiceImp<User> implements UserService {
	@Override
	@Resource(name = "userDao")
	public void setDao(BaseDao<User> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	@Override
	public boolean isRegisted(String email) {
		String hql = "from User u where u.email = ?" ;
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValid(list) ;
	}

	@Override
	public User validateLoginInfo(String email, String md5) {
		String hql="from User u where u.email=? and u.password=?";
			List<User> list=this.findEntityByHQL(hql, email,md5);
		return  ValidateUtil.isValid(list)?list.get(0):null;
	}
}
