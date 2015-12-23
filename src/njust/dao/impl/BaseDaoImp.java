package njust.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import njust.dao.BaseDao;
@SuppressWarnings("unchecked")
public abstract class BaseDaoImp<T> implements BaseDao<T> {
	//×¢Èë
	@Resource
	private  SessionFactory sf;
	private Class<T> clazz;
	public BaseDaoImp(){
		ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class<T>)pt.getActualTypeArguments()[0];
	}
	
	@Override
	public void saveEntity(T t) {
		sf.getCurrentSession().save(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		sf.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void updateEntity(T t) {
		sf.getCurrentSession().update(t);
	}

	@Override
	public void deleteEntity(T t) {
		sf.getCurrentSession().delete(t);
	}

	@Override
	public void batchEntityByHql(String hql, Object... objects) {
		Query query=sf.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++)
		{
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

	@Override
	public T loadEntity(Integer id) {
		
		return (T) sf.getCurrentSession().load(clazz, id);
	}

	@Override
	public T getEntity(Integer id) {
		return (T) sf.getCurrentSession().get(clazz, id);
	}

	
	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query query=sf.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++)
		{
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}
	
}
