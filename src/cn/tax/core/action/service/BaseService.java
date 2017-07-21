package cn.tax.core.action.service;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.page.PageResult;
import cn.tax.core.util.QueryHelper;
import cn.tax.nsfw.info.entity.Info;

public interface BaseService<T> {

	//新增
	public void save(T entity);
	//更新
	public void update(T entity);
	//根据id删除
	public void delete(Serializable id);
	//根据id查找
	public T findObjectById(Serializable id);
	//查找列表
	public List<T> findObjects();
	//按照条件查询实体类型
	public List<T> findObjects(String hql, List<Object> parameters);
	//按照条件查询实体类型
	public List<T> findObjects(QueryHelper queryHelper);
	//分页查询
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);

	

}
