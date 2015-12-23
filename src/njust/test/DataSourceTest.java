package njust.test;

import njust.model.User;
import njust.service.SurveyService;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest {
	private static SurveyService ss;
	@BeforeClass
	public static void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans0.xml");
		ss=(SurveyService) ac.getBean("surveyService");
		
	}
	@Test
	public void getConnection() {
		User u=new User();
		u.setId(1);
		ss.newSurvey(u);
	}

}
