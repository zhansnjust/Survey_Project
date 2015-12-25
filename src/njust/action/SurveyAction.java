package njust.action;

import java.util.List;

import javax.annotation.Resource;

import njust.model.Survey;
import njust.model.User;
import njust.service.SurveyService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey>implements UserAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private SurveyService surveyService;
	private List<Survey> mySurveys;
	private User user;
	private Integer sid;
	public String mySurveys()
	{
		this.mySurveys=surveyService.findSurveys(user);
		return "mySurveyList";
	}
	public String newSurvey()
	{
		model=surveyService.newSurvey(user);
		return "desigSurvey";
	}
	/**
	 * 设计调查
	 * @return
	 */
	public String designSurvey()
	{
		this.model = surveyService.getSurveyWithChildren(sid);
		return "desigSurvey";
	}
	
	public String editSurvey()
	{
		this.model=surveyService.getSrvey(sid);
		return "editSurvey";
	}
	public String updateSurvey()
	{
		this.sid=model.getId();
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}
	
	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}
	@Override
	public void setUser(User user) {
		this.user=user;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
}
