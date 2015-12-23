package njust.intercetor;

import njust.action.BaseAction;
import njust.action.LoginAction;
import njust.action.RegAction;
import njust.action.UserAware;
import njust.model.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginIntercetor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		@SuppressWarnings("rawtypes")
		BaseAction action = (BaseAction) arg0.getAction();
		if ((action instanceof LoginAction) || (action instanceof RegAction))
			return arg0.invoke();
		else {
			User u = (User) arg0.getInvocationContext().getSession().get("user");
			if(null==u)
				return "login";
			else
			{
				if(action instanceof UserAware)
					((UserAware)action).setUser(u);
				return arg0.invoke();
			}
		}

	}

}
