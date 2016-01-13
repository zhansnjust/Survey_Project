package njust.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import njust.dao.BaseDao;
import njust.model.User;
import njust.service.RoleService;
import njust.service.UserService;
import njust.utils.ValidateUtil;
import njust.model.Role;

@Service("userService")
public class UserServiceImp extends BaseServiceImp<User> implements UserService {
	@Resource
	private RoleService roleService;
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

	@Override
	public void clearAuthorize(Integer userId) {
		this.getEntity(userId).getRoles().clear();
	}

	@Override
	public void updateAuthorize(User model, Integer[] ownRoleIds) {
		User user=this.getEntity(model.getId());
		if(!ValidateUtil.isValid(ownRoleIds))
			user.getRoles().clear();
		else
		{
			List<Role> roles=roleService.findRolesInRange(ownRoleIds);
			user.setRoles(new HashSet<Role>(roles));
		}
	}
}
