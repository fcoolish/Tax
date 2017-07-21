package cn.tax.nsfw.complain.service;

import java.util.List;
import java.util.Map;

import cn.tax.core.action.service.BaseService;
import cn.tax.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {

	//自动受理
	public void autoDeal();

	/**
	 * 根据年份获取每个月的投诉数
	 * @param year
	 * @return	统计数据
	 */
	public List<Map> getAnnualStatisticDataByYear(int year);
}
