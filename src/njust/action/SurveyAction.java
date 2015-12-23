package njust.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import njust.model.Survey;
import njust.model.User;
import njust.service.SurveyService;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey>implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private SurveyService surveyService;
	private Map<String, Object> session;
	private List<Survey> mySurveys;
	public String mySurveys()
	{
		User user=(User) session.get("user");
		this.mySurveys=surveyService.findSurveys(user);
		return "mySurveyList";
	}
	public String newSurvey()
	{
		User user=(User) session.get("user");
		model=surveyService.newSurvey(user);
		return "desigSurvey";
	}
	
	
	
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}
	
}
