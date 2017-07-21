package cn.tax.core.util;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {

	//from子句
	private String fromClause="";
	//where子句
	private String whereClause="";
	//order子句
	private String orderClause="";
	
	private List<Object>parameters;
	//排序顺序
	public static String ORDER_BY_DESC = "DESC"; //降序
	public static String ORDER_BY_ASC = "ASC";//升序
	
	/*
	 * 构造from子句
	 */
	public QueryHelper(Class clazz,String alias)
	{
			fromClause = "FROM "+clazz.getSimpleName()+" "+alias;	
	}
	
	/**
	 * 构造where子句
	 * @param condition 查询条件语句：例如i.title like ?
	 * @param params	查询语句中?对应的查询条件值
	 */
	public void addCondition(String condition,Object... params)
	{
		
		if(whereClause.length()>1)//非第一次添加查询条件
		{
			whereClause += " AND "+ condition;
		}
		else //第一个查询条件
		{
			whereClause +=" WHERE " +condition;
		}
		
		//设置查询条件值到查询条件集合中
		if(parameters==null)
		{
			parameters = new ArrayList<Object>();
			
		}
		if(params!=null)
		{
			for(Object param:params)
			{
				parameters.add(param);
			}
		}
	}
	/**
	 * 构造order by子句
	 * @param property
	 * @param order 排序顺序
	 */
	public void addOrderByProperty(String property,String order)
	{
		//order 
		if(orderClause.length()>1)//非第一次排序属性
		{
			orderClause += ","+property+" "+ order;
		}
		else //第一个查询条件
		{
			orderClause =" ORDER BY " +property+" "+order;
		}
	}
	
	//查询hql语句
	public String getQueryListHql()
	{
		return fromClause+whereClause+orderClause;
	}
	
	//查询统计数的hql语句
		public String getQueryCountHql()
		{
			return "SELECT COUNT(*) "+ fromClause + whereClause;
		}
	//查询hql语句对应的查询条件集合
	public List<Object> getParamerers()
	{
		return parameters;
	}
}
