package njust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import njust.dao.BaseDao;
import njust.service.BaseService;

public abstract class BaseServiceImp<T> implements BaseService<T> {

	private  BaseDao<T> dao;
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}
	@Override
	public void saveEntity(T t) {
		// TODO Auto-generated method stub
		dao.saveEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	@Override
	public void batchEntityByHql(String hql, Object... objects) {
		dao.batchEntityByHql(hql, objects);
	}

	@Override
	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}

}
