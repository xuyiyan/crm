package com.xdl.system.service;

import com.xdl.system.entity.User;


/**
 * CRM系统用户登录接口
 */
public interface IUserLoginService {

	/**
	 * 验证用户手机号/邮箱是否存在
	 * @param phone
	 * @return
	 */
	public User authLoginNameIsExist(String email, String phone);
	
	/**
	 * 查询密码是否匹配。
	 */
	public boolean isPasswordMatch(Long userid, String password);
	
	
}
