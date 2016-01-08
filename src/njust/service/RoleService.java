package njust.service;

import njust.model.Role;


/**
 * roleService
 */
public interface RoleService extends BaseService<Role> {

	/**
	 */
	public void saveOrUpdateRole(Role model, Integer[] ownRightIds);
	
}
