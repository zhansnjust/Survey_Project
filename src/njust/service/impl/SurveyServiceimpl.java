package njust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import njust.dao.BaseDao;
import njust.model.Answer;
import njust.model.Page;
import njust.model.Question;
import njust.model.Survey;
import njust.model.User;
import njust.service.SurveyService;

@Service("surveyService")
public class SurveyServiceimpl implements SurveyService {
	@Resource(name = "surveyDao")
	private BaseDao<Survey> surveyDao;
	@Resource(name = "pageDao")
	private BaseDao<Page> pageDao;
	@Resource(name = "questionDao")
	private BaseDao<Question> questionDao;
	@Resource
	private BaseDao<Answer> answerDao;
	@Override
	public List<Survey> findSurveys(User user) {
		String hql = "from Survey s where s.user.id=?";
		return surveyDao.findEntityByHQL(hql, user.getId());
	}

	@Override
	public Survey newSurvey(User user) {
		Survey s = new Survey();
		Page p = new Page();
		s.setUser(user);
		p.setSurvey(s);
		s.getPages().add(p);
		surveyDao.saveEntity(s);
		return s;
	}

	@Override
	public Survey getSrvey(Integer id) {
		return surveyDao.getEntity(id);
	}
	//强行初始化survey的page对象，防止懒加载异常
	public Survey getSurveyWithChildren(Integer sid){
		Survey s = this.getSrvey(sid);
		//强行初始化pages和questions集合
		for(Page p : s.getPages()){
			p.getQuestions().size();
		}
		return s; 
	}

	@Override
	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
	}

	@Override
	public void saveOrUpdatePage(Page model) {
		pageDao.saveOrUpdateEntity(model);
	}

	@Override
	public Page getPage(Integer pid) {
		return pageDao.getEntity(pid);
	}

	@Override
	public void saveOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}

	@Override
	public void deleteQuestion(Integer qid) {
		String hql="delete from Answer a where a.questionId=?";
		answerDao.batchEntityByHql(hql, qid);
		hql="delete from Question q where q.id=?";
		questionDao.batchEntityByHql(hql, qid);
	}

	@Override
	public void deletePage(Integer pid) {
		String hql="delete from  Answer a where a.questionId in (select q.id from Question q where q.page.id=?)";
		answerDao.batchEntityByHql(hql, pid);
		hql="delete from Question q where q.page.id=?";
		questionDao.batchEntityByHql(hql, pid);
		hql="delete from Page p where p.id=? ";
		pageDao.batchEntityByHql(hql, pid);
	}

	@Override
	public void deleteSurvey(Integer sid) {
		//hibernate 在写操作的时候不许两级以上链接， 读可以
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHql(hql, sid);
		//hibernate 在写操作的时候不许两级以上链接， 读可以
		hql="delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)";
		questionDao.batchEntityByHql(hql, sid);
		hql="delete from Page p where p.survey.id=?";
		pageDao.batchEntityByHql(hql, sid);
		hql="delete from Survey s where s.id=?";
		surveyDao.batchEntityByHql(hql, sid);
	}

	@Override
	public Question getQuestion(Integer qid) {

		return questionDao.getEntity(qid);
	}

}
