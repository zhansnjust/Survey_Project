package njust.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import njust.model.Survey;
import njust.model.User;
import njust.service.SurveyService;
import njust.utils.ValidateUtil;

import org.apache.struts2.ServletActionContext;
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
	private File logoPhoto;
	private String logoPhotoFileName;
	private String logoPhotoContentType;
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
	public String deleteSurvey()
	{
		surveyService.deleteSurvey(sid);
		return "findMySurveyAction";
	}
	public String clearAnswers(){
		surveyService.deleteAnswer(sid);
		return "findMySurveyAction";
	}
	public String toggleStatus()
	{
		surveyService.toogleStatus(sid);
		return "findMySurveyAction";
	}
	public String toAddLogoPage()
	{
		return "toAddLoginPage";
	}
	public String doAddLogo()
	{
		if(ValidateUtil.isValid(logoPhotoFileName)){
			//1.实现上传
			// /upload文件夹真实路径
			String dir = ServletActionContext.getServletContext().getRealPath("/upload");
			//扩展名
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			//纳秒时间作为文件名
			long l = System.nanoTime();
			File newFile = new File(dir,l + ext);
			//文件另存为
			logoPhoto.renameTo(newFile);
			
			//2.更新路径
			surveyService.updateLogoPhotoPath(sid,"/upload/" + l + ext);
		}
		return "designSurveyAction" ;
	}
	public boolean photoExists(){
		String path = model.getLogoPhotoPath();
		if(ValidateUtil.isValid(path)){
			String absPath = ServletActionContext.getServletContext().getRealPath(path);
			File file = new File(absPath);
			return file.exists();
		}
		return false ;
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

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}
	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}
	public String getLogoPhotoContentType() {
		return logoPhotoContentType;
	}
	public void setLogoPhotoContentType(String logoPhotoContentType) {
		this.logoPhotoContentType = logoPhotoContentType;
	}
	public File getLogoPhoto() {
		return logoPhoto;
	}
	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}
	
}
