/**
 * 
 */
package cn.tax.nsfw.complain.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tax.core.action.service.impl.BaseServiceImpl;
import cn.tax.core.page.PageResult;
import cn.tax.core.util.QueryHelper;
import cn.tax.nsfw.complain.dao.ComplainDao;
import cn.tax.nsfw.complain.entity.Complain;
import cn.tax.nsfw.complain.service.ComplainService;

/**
 * @author fcoolish
 *
 */
 @Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements
		ComplainService {

	
	private ComplainDao complainDao;
    @Resource
	public void setComplain(ComplainDao complainDao) {
		// TODO Auto-generated method stub
    	super.setBaseDao(complainDao);
    	this.complainDao = complainDao;
	}
	@Override
	public void autoDeal() {
		// TODO Auto-generated method stub
		Calendar cal =Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);//设置当前时间的日期为1号
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		//1、查询本月之前的待受理的投诉列表
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		queryHelper.addCondition("c.state=?", Complain.COMPLAIN_STATE_UNDONE);
		queryHelper.addCondition("c.compTime < ?", cal.getTime());//本月1号0时0分0秒
		List<Complain> list = findObjects(queryHelper);
		if(list!=null&&list.size()>0)
		{
			//2、更新投诉信息的状态为已失效
			for(Complain comp:list)
			{
				comp.setState(Complain.COMPLAIN_STATE_INVALID);
				update(comp);
			}
		}
		
	}
	@Override
	public List<Map> getAnnualStatisticDataByYear(int year) {
		// TODO Auto-generated method stub
		List<Map>resList = new ArrayList<Map>();
		//1、获取统计数据
		List<Object[]>list = complainDao.getAnnualStatisticDataByYear(year);
		//2、格式化统计结果
		if(list!=null&list.size()>0)
		{
			Calendar cal=Calendar.getInstance();
			
			//是否当前年份
			boolean isCurYear= (cal.get(Calendar.YEAR)==year);
			int curMonth = cal.get(Calendar.MONTH+1);//当前月份
			int temMonth = 0;
			Map<String, Object>map = null;
			for(Object[] obj:list)
			{
				temMonth = Integer.valueOf(obj[0]+"");
				map = new HashMap<String, Object>();
				map.put("label", temMonth+"月");
				if(isCurYear)//当前年份
				{
					//当前年份：如果月份已过的则直接取投诉数并且值为空或Null时则为0;
					//如果月份没到则设置为空
					if(temMonth>curMonth)
					{
						//未到月份
						map.put("value","");
					}
					else 
					{
						map.put("value",obj[1]==null?"0":obj[1]);
					}
				}
				else 
				{//非当前年份
					map.put("value",obj[1]==null?"0":obj[1]);

				}
				resList.add(map);
			}
		}
		return resList;
	}
	

	

}
