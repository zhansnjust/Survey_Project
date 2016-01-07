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
	/**
	 * 删问题的同事删除答案
	 * @param qid
	 */
	void deleteQuestion(Integer qid);
	/**
	 * 删除页面， 同时删除问题和答案
	 * @param pid
	 */
	void deletePage(Integer pid);
	/**
	 * 下面的都要删除
	 * @param sid
	 */
	void deleteSurvey(Integer sid);
	Question getQuestion(Integer qid);
	void deleteAnswer(Integer sid);
	void toogleStatus(Integer sid);
	void updateLogoPhotoPath(Integer sid, String string);
}
