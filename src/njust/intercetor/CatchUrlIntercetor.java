package njust.intercetor;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import njust.service.RightService;
import njust.utils.ValidateUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CatchUrlIntercetor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9094596208613911327L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy=invocation.getProxy();
		//名字空间
		String ns=proxy.getNamespace();
		//actionName
		String actionName=proxy.getActionName();
		if(!ValidateUtil.isValid(ns)||"/".equals(ns))
			ns="";
		String url=ns+"/"+actionName;
		//取得spring容器,两种方法
		//ApplicationContext ac=(ApplicationContext) action.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		RightService rs = (RightService) ac.getBean("rightService");
		rs.appendRightByURL(url);
		return invocation.invoke();
	}

}
