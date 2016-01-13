package njust.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户类
 */
public class User {
	private Integer id;
	private String email;
	private String name;
	private String password;
	private String nickName;
	// 注册时间
	private Date regDate = new Date();
	private Set<Role> roles = new HashSet<Role>();
	// 权限总和
	private long rightSum[];
	private boolean superAdmin;

	public Integer getId() {
		return id;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public long[] getRightSum() {
		return rightSum;
	}

	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}

	public void calulateRightSum() {
		int pos = 0;
		long rightCode = 0;
		for (Role role : roles) {
			// 判断超级管理员
			if ("-1".equals(role.getRoleValue())) {
				this.superAdmin = true;
				roles = null;
				return;
			}
			for (Right r : role.getRights()) {
				pos = r.getRightPos();
				rightCode = r.getRightCode();
				rightSum[pos] = rightSum[pos] | rightCode;
			}
		}
		// 释放资源，把角色集合制空
		roles = null;
	}

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	// 判断是否有权限
	public boolean hasRight(Right r) {
		int pos = r.getRightPos();
		long rightCode = r.getRightCode();
		return (this.rightSum[pos] & rightCode) == 0 ? false : true;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", nickName="
				+ nickName + "]";
	}

}
