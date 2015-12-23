package njust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import njust.dao.BaseDao;
import njust.model.Page;
import njust.model.Survey;
import njust.model.User;
import njust.service.SurveyService;

@Service("surveyService")
public class SurveyServiceimpl implements SurveyService {
	@Resource(name = "surveyDao")
	private BaseDao<Survey> surveyDao;
	@Resource(name = "pageDao")
	private BaseDao<Page> pageDao;

	@Override
	public List<Survey> findSurveys(User user) {
		String hql = "from Survey s where s.user.id=?";
		return surveyDao.findEntityByHQL(hql, user.getId());
	}

	@Override
	public Survey newSurvey(User user) {
		Survey s = new Survey();
		Page p = new Page();
		//…Ë÷√πÿ¡™
		s.setUser(user);
		p.setSurvey(s);
		s.getPages().add(p);
		surveyDao.saveEntity(s);
		return s;
	}

}
