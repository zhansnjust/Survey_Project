package njust.action;

import javax.annotation.Resource;

import njust.model.Page;
import njust.model.Question;
import njust.service.SurveyService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = -7940590825700062942L;
	private Integer pid;
	private Integer sid;
	private Integer qid;
	@Resource
	private SurveyService surveyService;
	public String toSelectQuestionType()
	{
		result="selectQuestionType.jsp";
		return "success";
	}
	public String toDesignQuestionPage()
	{
		
		return ""+model.getQuestionType();
	}
	
	public String saveOrUpdateQuestion()
	{
		Page p=new Page();
		p.setId(pid);
		model.setPage(p);
		surveyService.saveOrUpdateQuestion(model);
		return "designSurveyAction";
	}
	public String editQuestion()
	{
		this.model=surveyService.getQuestion(qid);
		return ""+model.getQuestionType();
	}
	/**
	 * 删除问题
	 * @return
	 */
	public String deleteQuestion()
	{
		surveyService.deleteQuestion(qid);
		return "designSurveyAction" ;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	
}
