package cn.tax.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;



import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tax.core.dao.BaseDao;
import cn.tax.core.page.PageResult;
import cn.tax.core.util.QueryHelper;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	Class<T> clazz;
	
	public BaseDaoImpl ()
	{
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();//BaseDaoImpl<T>User
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public  void save(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(findObjectById(id));
	}

	@Override
	public T findObjectById(Serializable id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(clazz,id);
	}

	
	
	@Override
	public List<T> findObjects() {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("FROM "+clazz.getSimpleName());
		return query.list();
	}
	
	@Override
	public List<T> findObjects(String hql,List<Object>parameters) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql);
		if(parameters!=null)
		{
			for (int i = 0; i < parameters.size(); i++) 
			{
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object>parameters=queryHelper.getParamerers();
		if(parameters!=null)
		{
			for (int i = 0; i < parameters.size(); i++) 
			{
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object>parameters=queryHelper.getParamerers();
		if(parameters!=null)
		{
			for (int i = 0; i < parameters.size(); i++) 
			{
				query.setParameter(i, parameters.get(i));
			}
		}
		if(pageNo<1)pageNo=1;
		
		query.setFirstResult((pageNo-1)*pageSize);//设置数据起始索引号
		query.setMaxResults(pageSize);
		List items = query.list();
		//获取总记录数
		
		Query queryCount = getSession().createQuery(queryHelper.getQueryCountHql());
		if(parameters!=null)
		{
			for (int i = 0; i < parameters.size(); i++) 
			{
				queryCount.setParameter(i, parameters.get(i));
			}
		}
		long totalCount = (Long)queryCount.uniqueResult();
		return new PageResult(totalCount, pageNo, pageSize, items);
	}
	
	
	
}
