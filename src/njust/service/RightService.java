package njust.service;

import java.util.List;

import njust.model.Right;


/**
 * rightService
 */
public interface RightService extends BaseService<Right> {

	
	public void saveOrUpdateRight(Right model);

	
	public void appendRightByURL(String url);

	
	public void batchUpdateRights(List<Right> allRights);

	
	public List<Right> findRightsInRange(Integer[] ids);
	
}
