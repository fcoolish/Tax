package cn.tax.nsfw.user.entity;

import java.io.Serializable;

import cn.tax.nsfw.role.entity.Role;

public class UserRoleId implements Serializable {
	
	private Role role;
	
	private String userId;
	
	public UserRoleId(Role role, String userId) {
		super();
		this.role = role;
		this.userId = userId;
	}
	
	public UserRoleId() {
		super();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserRoleId other = (UserRoleId) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
}
