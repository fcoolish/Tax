package cn.tax.nsfw.role.entity;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable 
{
	private String roleId;
	private String name;
	private String state;
	private Set<RolePrivilege>rolePrivileges;
	//用户状态
	public static String ROLE_STATE_VALID = "1";//有效
	public static String ROLE_STATE_INVALID = "0";//无效
	
	public Role(String roleId, String name, String state,
			Set<RolePrivilege> rolePrivileges) {
		this.roleId = roleId;
		this.name = name;
		this.state = state;
		this.rolePrivileges = rolePrivileges;
	}
	
	public Role() {
		super();
	}

	public Role(String roleId) {
		// TODO Auto-generated constructor stub
		this.roleId=roleId;
	}

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}
	public void setRolePrivileges(Set<RolePrivilege>rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}
	
}
