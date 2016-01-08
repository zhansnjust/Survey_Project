package njust.service;

import java.util.List;

public interface BaseService<T> {
		//
		public void saveEntity(T t);
		public void saveOrUpdateEntity(T t);
		public void updateEntity(T t);
		public void deleteEntity(T t);
		public void batchEntityByHql(String hql,Object ...objects);
		
		//
		public T loadEntity(Integer id);
		public T getEntity(Integer id);
		public List<T> findEntityByHQL(String hql,Object ...objects);
		
		public List<T> findAllEntities();
		public Object uniqueResult(String hql,Object...objects);
}
