package cn.tax.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.functors.ChainedClosure;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.tax.core.constant.Constant;
import cn.tax.core.permission.PermissionCheck;
import cn.tax.nsfw.user.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String uri = request.getRequestURI();
		//判断当前请求地址是否是登陆的请求地址
		if(!uri.contains("sys/login_"))
		{
			//非登录请求
			if(request.getSession().getAttribute(Constant.USER)!=null)
			{
				//说明已经登陆过
				//判断是否访问纳税服务子系统
				if(uri.contains("/nsfw/"))
				{
					//访问纳税服务子系统
					User user =(User)request.getSession().getAttribute(Constant.USER);
					//获取spring容器
					WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
					PermissionCheck pc=(PermissionCheck) applicationContext.getBean("permissionCheck");
					if(pc.isAccessiable(user,"nsfw"))
					{
						//说明有权限
						chain.doFilter(request, response);

						
					}
					else 
					{
						//没有权限，就跳转到没有权限提示页面
						response.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
					}
				}
				
				else 
				{
					//非访问纳税服务子系统，则直接放行
					chain.doFilter(request, response);
				}
			}
			
		else{
					//没有登录，跳转到登录页面
					response.sendRedirect(request.getContextPath() + "/sys/login_toLoginUI.action");
			}		
		}
		else 
		{
			//登陆请求，直接放行
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
