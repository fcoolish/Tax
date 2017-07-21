package cn.tax.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable {
	
	private RolePrivilegeId id;
	public RolePrivilege() {
	}

	public RolePrivilege(RolePrivilegeId id) {
		this.id = id;
	}

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}
}
