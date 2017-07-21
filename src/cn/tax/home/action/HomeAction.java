package cn.tax.home.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.tax.core.constant.Constant;
import cn.tax.core.util.QueryHelper;
import cn.tax.nsfw.complain.entity.Complain;
import cn.tax.nsfw.complain.service.ComplainService;
import cn.tax.nsfw.info.entity.Info;
import cn.tax.nsfw.info.service.InfoService;
import cn.tax.nsfw.user.entity.User;
import cn.tax.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author  fcoolish
 *
 */
public class HomeAction extends ActionSupport {
	
	@Resource
	private UserService userService;
	
	@Resource
	private InfoService infoService;
	
	@Resource
	private ComplainService complainService;
	private Complain comp;
	private Info info;
	//跳转到首页
	private Map<String, Object>return_map;
	public String execute()
	{
		//加载信息
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper1 = new QueryHelper(Info.class, "i");
		queryHelper1.addOrderByProperty("i.createTime", QueryHelper.ORDER_BY_DESC);
		List<Info>infoList = infoService.getPageResult(queryHelper1, 1, 5).getItems();
		ActionContext.getContext().getContextMap().put("infoList",infoList);

		User user = (User)ServletActionContext.getRequest().getSession().getAttribute(Constant.USER);
		//加载我的投诉信息列表
		//加载状态集合
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);

		QueryHelper queryHelper2 = new QueryHelper(Complain.class, "c");
		queryHelper2.addCondition("c.compName = ?", user.getName());
		//根据投诉时间升序排序
		queryHelper2.addOrderByProperty("c.compTime", QueryHelper.ORDER_BY_ASC);
		queryHelper2.addOrderByProperty("c.state", QueryHelper.ORDER_BY_DESC);

		List<Complain>complainList = complainService.getPageResult(queryHelper2, 1, 6).getItems();
		ActionContext.getContext().getContextMap().put("complainList",complainList);

		return "home";
	}
	//跳转到我要投诉
	public String complainAddUI()
	{
		return "complainAddUI";
	}
	
	/**
	 * 通过json对象输出数据
	public void getUserJson()
	{
		try {
			//1、输出用户获取部门
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept))
			{
				//2、根据部门查询用户列表
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept like ?", "%" + dept);
				userList = userService.findObjects(queryHelper);
				//创建json对象
				JSONObject json = new JSONObject();
				json.put("msg", "success");
				json.accumulate("userList", userList);
				
				//3、输出用户列表以json格式字符串形式输出
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(json.toString().getBytes("utf-8"));
				outputStream.close();
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}**/
	public String getUserJson2()
	{
		try {
			//1、输出用户获取部门
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept))
			{
				//2、根据部门查询用户列表
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept like ?",  "%"+dept);
				 
				return_map = new HashMap<String, Object>();
				return_map.put("msg","success");
				return_map.put("userList", userService.findObjects(queryHelper));
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//保存投诉
	public void complainAdd()
	{
		try {
			if(comp!=null)
			{
				//设置默写投诉状态为待受理
				comp.setState(Complain.COMPLAIN_STATE_UNDONE);
				comp.setCompTime(new Timestamp(new Date().getTime()));
				complainService.save(comp);
				//输出投诉结果
				//3\输出用户列表以json格式字符串形式输出
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("success".getBytes("utf-8"));
				outputStream.close();
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//查看信息
	public String infoViewUI()
	{
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		if(info!=null)
		{
			info = infoService.findObjectById(info.getInfoId());
		}
		return "infoViewUI";
	}
	
	   //查看投诉信息
		public String complainViewUI()
		{
			ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
			if(comp!=null)
			{
				comp = complainService.findObjectById(comp.getCompId());
			}
			return "complainViewUI";
		}
	
	public Map<String, Object> getReturn_map() {
		return return_map;
	}
	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}
	public Complain getComp() {
		return comp;
	}
	public void setComp(Complain comp) {
		this.comp = comp;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
	
}
