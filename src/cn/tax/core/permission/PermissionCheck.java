package cn.tax.core.permission;

import cn.tax.nsfw.user.entity.User;

public interface PermissionCheck {
	/**
	 * 判断用户是否有code的权限
	 * @param user 用户
	 * @param code 子系统的权限标识符
	 * @return true or false
	 */

	boolean isAccessiable(User user, String code);
	

}
