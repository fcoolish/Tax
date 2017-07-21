package cn.tax.nsfw.complain.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;

import cn.tax.core.dao.impl.BaseDaoImpl;
import cn.tax.nsfw.complain.dao.ComplainDao;
import cn.tax.nsfw.complain.entity.Complain;

public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements
		ComplainDao {

	@Override
	public List<Object[]> getAnnualStatisticDataByYear(int year) {
		// TODO Auto-generated method stu
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT imonth, count(comp_id)")
		.append(" FROM tmonth LEFT JOIN complain ON imonth=month(comp_time)")
		.append(" And YEAR(comp_time)=?")
		.append(" Group by imonth")
		.append(" Order by imonth");
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		sqlQuery.setParameter(0, year);
		
		return sqlQuery.list();
	}

	

}
