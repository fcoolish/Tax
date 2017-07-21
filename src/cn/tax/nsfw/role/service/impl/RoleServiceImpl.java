package cn.tax.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import cn.tax.core.action.service.impl.BaseServiceImpl;
import cn.tax.nsfw.role.dao.RoleDao;
import cn.tax.nsfw.role.entity.Role;
import cn.tax.nsfw.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	
	
	private RoleDao roleDao;
	
	
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}

	

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		//1、删除该角色对于的所有权限
		//2、更新角色及其权限
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		roleDao.update(role);
	}

	

}
