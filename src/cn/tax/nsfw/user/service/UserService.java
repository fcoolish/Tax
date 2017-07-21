package cn.tax.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.tax.core.action.service.BaseService;
import cn.tax.nsfw.user.entity.User;
import cn.tax.nsfw.user.entity.UserRole;

public interface UserService extends BaseService<User>
{

	
	
	//导出用户列表
	public void exportExcel(List<User>userList,ServletOutputStream outputStream);
	//导入用户列表
	public void importExcel(File userExcel,String userExcelFileName);
	//根据账号和用户id查询用户
	public List<User>findUserByAccountAndId(String id,String account);
	
	//保存用户及其对应的角色
	public void saveUserAndRole(User user, String...roleIds);
	
	//更新用户及其对应的角色
	public void updateUserAndRole(User user, String...roleIds);
	//根据用户id获取该用户对应的用户角色
	public List<UserRole> getUserRoleByUserId(String id);
	//根据用户与密码查询用户列表
	public List<User> findUserByAccountAndPass(String account, String password);
	
	
	
}
