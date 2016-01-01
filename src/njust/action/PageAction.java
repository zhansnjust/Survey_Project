package njust.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import njust.model.Page;
import njust.model.Survey;
import njust.service.SurveyService;
@Controller
@Scope("prototype")
public class PageAction  extends BaseAction<Page>{

	private static final long serialVersionUID = 1L;
	private Integer sid;
	private Integer pid;
	@Resource
	private SurveyService surveyService;
	public String toAddPage()
	{
		this.model=surveyService.getPage(sid);
		result="editPage.jsp";
		return "success";
	}
	public String saveOrUpdatePage()
	{
		Survey s=new Survey();
		s.setId(sid);
		model.setSurvey(s);
		surveyService.saveOrUpdatePage(model);
		return "designSurveyAction";
	}
	public String editPage()
	{
		
		result="editPage.jsp";
		return "success";
	}
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	
	
}
