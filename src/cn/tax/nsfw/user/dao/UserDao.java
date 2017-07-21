package cn.tax.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import cn.tax.core.dao.BaseDao;
import cn.tax.nsfw.user.entity.User;
import cn.tax.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> 
{
	public List<User>findUserByAccountAndId(String id,String account);
	
	//保存用户角色
	public void saveUserRole(UserRole userRole);

	//根据用户id删除所有用户角色
	public void deleteUserRoleByUserId(Serializable id);
	
	//根据用户id获取该用户对应的用户角色

	public List<UserRole> getUserRoleByUserId(String id);

	//根据用户与密码查询用户列表

	public List<User> findUserByAccountAndPass(String account, String password);
	
}
