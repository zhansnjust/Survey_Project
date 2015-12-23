package njust.service;

import java.util.List;

import njust.model.Survey;
import njust.model.User;

public interface SurveyService {

	List<Survey> findSurveys(User user);
	Survey newSurvey(User user);
}
