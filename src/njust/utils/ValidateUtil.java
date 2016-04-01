package njust.utils;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import njust.action.BaseAction;
import njust.action.UserAware;
import njust.model.Right;
import njust.model.User;

/**
 */
public class ValidateUtil {
	
	/**
	 * 字符是否为空
	 */
	public static boolean isValid(String src){
		if(src == null || "".equals(src.trim())){
			return false ;
		}
		return true ;
	}
	
	/**
	 * 集合是否为空
	 */
	public static boolean isValid(Collection<?> col){
		if(col == null || col.isEmpty()){			
			return false ;
		}
		return true ;
	}

	public static boolean isValid(Object[] paramType) {
		if(paramType==null||paramType.length==0)
			return false;
		return true;
	}
	/**
	 * 判断是否有权限
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean hasRight(String namespace,String actionName,HttpServletRequest req,BaseAction action){
		if(!ValidateUtil.isValid(namespace)
				|| "/".equals(namespace)){
			namespace = "" ;
		}
		//将超链接的参数部分滤掉 ?xxxx
		if(actionName.contains("?")){
			actionName = actionName.substring(0, actionName.indexOf("?"));
		}
		String url = namespace + "/" + actionName ;
		HttpSession session = req.getSession();
		
		ServletContext sc = session.getServletContext();
		Map<String, Right> map = (Map<String, Right>) sc.getAttribute("all_rights_map");
		Right r = map.get(url);
		
		//公共资源?
		if(r == null || r.isCommon()){
			return true ;
		}
		else{
			User user = (User) session.getAttribute("user");
			//登陆?
			if(user == null){
				return false ;
			}
			else{
				//userAware处理
				if(action != null && action instanceof UserAware){
					((UserAware)action).setUser(user);
				}
				//超级管理员
				if(user.isSuperAdmin()){
					return true ;
				}
				else{
					//有权限?
					if(user.hasRight(r)){
						return true ;
					}
					else{
						return false ;
					}
				}
			}
		}
	}
}
