package njust.dao;

import java.util.List;

public interface BaseDao<T> {
	//Ð´
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityByHql(String hql,Object ...objects);
	
	//¶Á
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql,Object ...objects);
	
}
