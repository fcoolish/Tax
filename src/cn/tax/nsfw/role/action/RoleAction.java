package cn.tax.nsfw.role.action;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.tax.core.action.BaseAction;
import cn.tax.core.constant.Constant;
import cn.tax.core.util.QueryHelper;
import cn.tax.nsfw.role.entity.Role;
import cn.tax.nsfw.role.entity.RolePrivilege;
import cn.tax.nsfw.role.entity.RolePrivilegeId;
import cn.tax.nsfw.role.service.RoleService;


public class RoleAction extends BaseAction
{
	
	
	@Resource
	private RoleService roleService;

	private Role role;
	private String[] privilegeIds;
	private String strName;
	
	
	
	
	//列表页面
	public String listUI()
	{
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		QueryHelper queryHelper = new QueryHelper(Role.class, "r");
		try {
			if (role != null) {
				if (StringUtils.isNotBlank(role.getName())) {
					role.setName(URLDecoder.decode(role.getName(), "utf-8"));
					queryHelper.addCondition("r.name like ?",
							"%" + role.getName() + "%");

				}

			}
			
			pageResult = roleService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "listUI";
	} 
	//跳转到新增页面
	public String addUI()
	{
		//加载权限集合
		 ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
	}
	//保存新增
	public String add() {	
		try {
			if (role != null) {
				//处理权限保存
				if(privilegeIds!=null)
				{
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for(int i=0;i<privilegeIds.length;i++)
					{
						set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.save(role);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "list";
	}
	//跳转到编辑页面
	public String editUI() {
		 ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		if(role!=null&&role.getRoleId()!=null)
		{
			strName = role.getName();
			role = roleService.findObjectById(role.getRoleId());
			//处理数据回显
			 if(role.getRolePrivileges()!=null)
			 {
				privilegeIds = new String[role.getRolePrivileges().size()];
				int i=0;
				for(RolePrivilege rp:role.getRolePrivileges())
				{
					
					privilegeIds[i++] = rp.getId().getCode();
				}
			 }
		}
		return "editUI";
		}
	
	//保存编辑
	public String edit() {
		
			if(role!=null)
			{
				//处理权限保存
				if(privilegeIds!=null)
				{
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for(int i=0;i<privilegeIds.length;i++)
					{
						set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				
				roleService.update(role);
			  }
			
			
			
	
		return "list";
	}
	//删除
	public String delete() {
		if(role!=null)
		{
			roleService.delete(role.getRoleId());
		}
		return "list";
	}
	//批量删除
	public String deleteSelected() {
		if(selectedRow!=null)
		{
			for(String id:selectedRow)
			{
				roleService.delete(id);
			}
		}
		return "list";
	}
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	
		
	
}