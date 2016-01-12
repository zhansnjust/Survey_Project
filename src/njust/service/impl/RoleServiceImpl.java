package njust.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import njust.dao.BaseDao;
import njust.model.Right;
import njust.model.Role;
import njust.service.RightService;
import njust.service.RoleService;
import njust.utils.DataUtil;
import njust.utils.StringUtil;
import njust.utils.ValidateUtil;
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImp<Role> implements
	RoleService {
	@Resource
	private RightService rightService;
	
	@Override
	@Resource(name="roleDao")
	public void setDao(BaseDao<Role> dao) {
		super.setDao(dao);
	}

	@Override
	public void saveOrUpdateRole(Role model, Integer[] ownRightIds) {
		if(!ValidateUtil.isValid(ownRightIds))
			model.getRights().clear();
		else
		{
			List<Right> rights=rightService.findRightsInRange(ownRightIds);
			model.setRights(new HashSet<Right>(rights));
		}
		this.saveOrUpdateEntity(model);
	}

	/**
	 * 查询不在指定范围中的角色
	 */
	public List<Role> findRolesNotInRange(Set<Role> roles) {
		if(!ValidateUtil.isValid(roles)){
			return this.findAllEntities();
		}
		else{
			String hql = "from Role r where r.id not in("+DataUtil.extractRightIds(roles)+")" ;
			return this.findEntityByHQL(hql);
		}
	}
	
	/**
	 * 查询在指定范围中的角色集合
	 */
	public List<Role> findRolesInRange(Integer[] ids){
		if(ValidateUtil.isValid(ids)){
			String hql = "from Role r where r.id in ("+StringUtil.arr2Str(ids)+")" ;
			return this.findEntityByHQL(hql);
		}
		return null ;
	}
}
