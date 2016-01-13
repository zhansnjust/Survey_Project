package njust.action;

import java.util.Map;

import javax.annotation.Resource;

import njust.model.User;
import njust.service.RightService;
import njust.service.UserService;
import njust.utils.DataUtil;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String , Object>  session;
	@Resource
	private RightService rightService;
	@Resource
	private UserService userService;
	public String doLogin()
	{
		return "success";
	}
	public void validateDoLogin() {

		User user=userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
		if(user==null)
			addActionError("email/password 错误");
		else
		{
			//初始化权限总和数组
			int maxPos=rightService.getMaxRightPos();
			user.setRightSum(new long[maxPos+1]);
			//计算权限总和
			user.calulateRightSum();
			session.put("user", user);
		}
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}
}
