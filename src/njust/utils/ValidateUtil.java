package njust.utils;

import java.util.Collection;

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
}
