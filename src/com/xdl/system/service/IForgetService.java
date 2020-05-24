package com.xdl.system.service;

import com.xdl.system.entity.Forget;

/**
 * 忘记密码接口
 */
public interface IForgetService {
	
	/**
	 * 保存忘记密码邮件链接信息
	 * @param forget
	 */
	public void saveForget(Forget forget);
	
	/**
	 * 修改忘记密码邮件链接信息
	 * @param forget
	 */
	public void updateForget(Forget forget);
	
	/**
	 * 通过用户id或者邮箱查询用户忘记密码链接匹配信息
	 * @param userid
	 * @param email
	 * @return
	 */
	public Forget queryForget(Long userid, String email);
	
	
}
