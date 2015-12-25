package njust.service;

import java.util.List;

import njust.model.Page;
import njust.model.Question;
import njust.model.Survey;
import njust.model.User;

public interface SurveyService {

	List<Survey> findSurveys(User user);
	Survey newSurvey(User user);
	public Survey getSrvey(Integer id);
	public Survey getSurveyWithChildren(Integer sid);
	void updateSurvey(Survey model);
	void saveOrUpdatePage(Page model);
	Page getPage(Integer pid);
	void saveOrUpdateQuestion(Question model);
}
