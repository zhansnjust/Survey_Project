package njust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import njust.dao.BaseDao;
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

}
