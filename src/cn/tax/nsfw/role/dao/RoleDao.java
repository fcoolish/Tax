package cn.tax.nsfw.role.dao;

import cn.tax.core.dao.BaseDao;
import cn.tax.nsfw.role.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	
	//删除角色对于的所有权限
	public void deleteRolePrivilegeByRoleId(String roleId);

}
