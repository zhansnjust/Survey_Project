package njust.service;

import njust.model.User;

public interface UserService extends BaseService<User> {
	public boolean isRegisted(String email);

	public User validateLoginInfo(String email, String md5);
}
