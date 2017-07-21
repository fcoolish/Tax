package cn.tax.nsfw.complain.dao;

import java.util.List;

import cn.tax.core.dao.BaseDao;
import cn.tax.nsfw.complain.entity.Complain;

public interface ComplainDao extends BaseDao<Complain> {

	public List<Object[]>getAnnualStatisticDataByYear(int year);

}
