package njust.utils;

import java.util.Collection;

/**
 * У�鹤����
 */
public class ValidateUtil {
	
	/**
	 * �ж��ַ�����Ч��
	 */
	public static boolean isValid(String src){
		if(src == null || "".equals(src.trim())){
			return false ;
		}
		return true ;
	}
	
	/**
	 * �жϼ��ϵ���Ч�� 
	 */
	public static boolean isValid(Collection<?> col){
		if(col == null || col.isEmpty()){			
			return false ;
		}
		return true ;
	}
}
