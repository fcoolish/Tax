package cn.tax.core.action.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.action.service.BaseService;
import cn.tax.core.dao.BaseDao;
import cn.tax.core.page.PageResult;
import cn.tax.core.util.QueryHelper;
import cn.tax.nsfw.info.dao.InfoDao;
import cn.tax.nsfw.info.entity.Info;

public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T>baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		baseDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);
	}

	@Override
	public T findObjectById(Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.findObjectById(id);
	}

	@Override
	public List<T> findObjects() {
		// TODO Auto-generated method stub
		return baseDao.findObjects();
	}


	@Override
	public List<T> findObjects(String hql, List<Object> parameters) {
		// TODO Auto-generated method stub
		return baseDao.findObjects(hql, parameters);
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		// TODO Auto-generated method stub
		return baseDao.findObjects(queryHelper);
	}

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.getPageResult(queryHelper,pageNo,
				pageSize) ;
	}
}
