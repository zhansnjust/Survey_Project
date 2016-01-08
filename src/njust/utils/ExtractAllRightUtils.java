package njust.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;

import njust.service.RightService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//提取所有权限的工具类
public class ExtractAllRightUtils {
	public static void main(String[] args) throws URISyntaxException, ClassNotFoundException {
		ClassLoader cl=ExtractAllRightUtils.class.getClassLoader();
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans0.xml");
		RightService rs = (RightService) ac.getBean("rightService");
		URL url=cl.getResource("njust/action");
		File file=new File(url.toURI());
		File files[]=file.listFiles();
		for(File f:files)
		{
			String fname=f.getName();
			if(fname.endsWith(".class")&&!fname.equals("BaseAction.class")&&!fname.equals("UserAware.class"))
				processAction(fname, rs);
		}
	}
	//处理action类，捕获所有的url地址，形成权限
	@SuppressWarnings("rawtypes")
	private static void processAction(String fname,RightService rs) {
		try {
			String pkgName = "njust.action" ;
			String simpleClassName = fname.substring(0, fname.indexOf(".class"));
			String className = pkgName  + "." + simpleClassName ;
			//得到具体类
			Class clazz = Class.forName(className);
			Method[] methods = clazz.getDeclaredMethods();
			Class retType = null ;
			String mname = null ;
			Class[] paramType = null ;
			String url = null ;
			for(Method m : methods){
				retType = m.getReturnType();//返回值类型
				mname = m.getName();        //方法名称
				paramType = m.getParameterTypes();//参数类型
				if(retType == String.class
						&& !ValidateUtil.isValid(paramType)
						&& Modifier.isPublic(m.getModifiers())){
					if(mname.equals("execute")){
						url = "/" + simpleClassName ;
					}
					else{
						url = "/" + simpleClassName + "_" + mname ;
					}
					System.out.println(url);
					rs.appendRightByURL(url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
