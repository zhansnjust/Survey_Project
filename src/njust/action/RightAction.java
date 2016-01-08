package njust.action;

import java.util.List;
import javax.annotation.Resource;
import njust.model.Right;
import njust.service.RightService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 * RightAction
 */
@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = 2546431181647311959L;
	
	private List<Right> allRights  ;
	
	private Integer rightId ;
	
	public Integer getRightId() {
		return rightId;
	}


	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}


	public List<Right> getAllRights() {
		return allRights;
	}


	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	@Resource(name="rightService")
	private RightService rightService ;

	/**
	 */
	public String findAllRights(){
		this.allRights = rightService.findAllEntities();
		return "rightListPage" ;
	} 
	
	/**
	 */
	public String toAddRightPage(){
		return "addRightPage" ;
	}
	
	/**
	 */
	public String saveOrUpdateRight(){
		rightService.saveOrUpdateRight(model);
		return "findAllRightAction" ;
	}
	
	/**
	 */
	public String editRight(){
		System.out.println("rightId="+rightId);
		this.model = rightService.getEntity(rightId);
		return "editRightPage" ;
	}
	
	/**
	 */
	public String deleteRight(){
		Right r = new Right();
		r.setId(rightId);
		rightService.deleteEntity(r);
		return "findAllRightAction" ;
	}
	
	/**
	 */
	public String batchUpdateRights(){
		rightService.batchUpdateRights(allRights);
		return "findAllRightAction" ;
	}
}