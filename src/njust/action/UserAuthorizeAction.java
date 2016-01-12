package njust.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import njust.model.Role;
import njust.model.User;
import njust.service.RoleService;
import njust.service.UserService;

@Controller
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6890914521532395852L;
	private List<User> allUsers;

	private Integer[] ownRoleIds;
	
	@Resource
	private UserService userService ;
	
	private Integer userId ;
	
	//用户没有的角色集合
	private List<Role> noOwnRoles ;
	
	@Resource
	private RoleService roleService ;
	/**
	 * 查询所有用户 
	 */
	public String findAllUsers(){
		this.allUsers = userService.findAllEntities();
		return "userAuthorizeListPage";
	}
	
	/**
	 * 修改授权
	 */
	public String editAuthorize(){
		this.model = userService.getEntity(userId);
		this.noOwnRoles = roleService.findRolesNotInRange(model.getRoles());
		return "userAuthorizePage" ;
	}
	
	/**
	 * 更新授权
	 */
	public String updateAuthorize(){
		userService.updateAuthorize(model,ownRoleIds);
		return "findAllUsersAction" ;
	}
	
	/**
	 * 清除授权
	 */
	public String clearAuthorize(){
		userService.clearAuthorize(userId);
		return "findAllUsersAction" ;
	}


	public List<User> getAllUsers() {
		return allUsers;
	}


	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}


	public Integer[] getOwnRoleIds() {
		return ownRoleIds;
	}


	public void setOwnRoleIds(Integer[] ownRoleIds) {
		this.ownRoleIds = ownRoleIds;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public List<Role> getNoOwnRoles() {
		return noOwnRoles;
	}


	public void setNoOwnRoles(List<Role> noOwnRoles) {
		this.noOwnRoles = noOwnRoles;
	}
	
	
}
