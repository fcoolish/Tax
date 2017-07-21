package cn.tax.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.tax.core.constant.Constant;
import cn.tax.nsfw.user.entity.User;
import cn.tax.nsfw.user.service.UserService;


import com.opensymphony.xwork2.ActionSupport;



public class LoginAction extends ActionSupport {

	@Resource
	private UserService userService;
	private User user;
	
	private String loginResult;
	
	//跳转到登录页面
	public String toLoginUI() {
		
		return "loginUI";
	}
	//跳转到没有权限提示页面
	public String toNoPermissionUI()
	{
		return "noPermissionUI";
	}
	
	//登录
	public String login()
	{
		if(user!=null)
		{
			if(StringUtils.isNotBlank(user.getAccount())&&StringUtils.isNotBlank(user.getPassword()))
			{
				//根据用户账号和密码查询用户列表
				List<User>list=userService.findUserByAccountAndPass(user.getAccount(), user.getPassword());
				if(list!=null&&list.size()>0)
				{
					User user = list.get(0);
					user.setUserRoles(userService.getUserRoleByUserId(user.getId()));
					ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
					//将用户登录记录到日志文件中
					Log log = LogFactory.getLog(getClass());
					log.info("用户名称为:"+user.getName()+"的用户登录了系统");
					//重定向跳转到系统首页
					return "home";
				}
			}
			else {
				loginResult = "账号或密码不能为空";
			}
		}
		else 
		{
			loginResult = "请输入账号和密码";
		}
		return "tologinUI";
	}

	//退出，清除
	public String logout()
	{
		//清除账号
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
		return toLoginUI();
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
}
