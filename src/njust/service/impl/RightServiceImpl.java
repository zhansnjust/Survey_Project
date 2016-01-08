package njust.service.impl;
import java.util.List;

import javax.annotation.Resource;

import njust.dao.BaseDao;
import njust.model.Right;
import njust.service.RightService;

import org.springframework.stereotype.Service;
@Service("rightService")
public class RightServiceImpl extends  BaseServiceImp<Right> implements RightService{

	@Override
	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}
	@Override
	public void saveOrUpdateRight(Right r) {
		//插入操作
				int pos = 0 ;
				long code = 1L ; 
				if(r.getId() == null){
//					String hql = "from Right r order by r.rightPos desc,r.rightCode desc" ;
//					List<Right> rights = this.findEntityByHQL(hql);
//					if(!ValidateUtil.isValid(rights)){
//						pos = 0;
//						code = 1L ;
//					}
//					else{
//						//得到最上面的right
//						Right top = rights.get(0);
//						int topPos = top.getRightPos();
//						long topCode = top.getRightCode();
//						//判断权限码是否到达最大值
//						if(topCode >= (1L << 60)){
//							pos = topPos + 1 ;
//							code = 1 ;
//						}
//						else{
//							pos = topPos ;
//							code = topCode << 1 ;
//						}
//					}
					//
					String hql = "select max(r.rightPos),max(r.rightCode) from Right r "
							+ "where r.rightPos = (select max(rr.rightPos) from Right rr)" ;
					Object[] arr = (Object[]) this.uniqueResult(hql);
					Integer topPos = (Integer) arr[0];
					Long topCode = (Long) arr[1];
					//没有权限
					if(topPos == null){
						pos = 0 ;
						code = 1L ;
					}
					else{
						//权限码是否到达最大值
						if(topCode >= (1L << 60)){
							pos = topPos + 1 ;
							code = 1L ;
						}
						else{
							pos = topPos ;
							code = topCode << 1 ;
						}
					}
					
					r.setRightPos(pos);
					r.setRightCode(code);
				}
				this.saveOrUpdateEntity(r);
	}

	@Override
	public void appendRightByURL(String url) {
		String hql="select count(*) from Right r where r.rightUrl=?";
		Long l=(Long) this.uniqueResult(hql, url);
		if(l==0)
		{
			Right right=new Right();
			right.setRightUrl(url);
			this.saveOrUpdateRight(right);
		}
	}

	@Override
	public void batchUpdateRights(List<Right> allRights) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Right> findRightsInRange(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
