package cn.tax.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilegeId implements Serializable {

	private Role role;	
	private String code;
	
	
	
	public RolePrivilegeId(Role role, String code) {
		this.role = role;
		this.code = code;
	}
	
	public RolePrivilegeId() {
		
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePrivilegeId other = (RolePrivilegeId) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
}
