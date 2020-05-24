package com.xdl.publics.logs.service;

import java.util.List;

import com.xdl.publics.logs.entity.Userlogs;
import com.xdl.publics.util.PageBean;


/**
 * 操作日志接口
 */
public interface ILogsService {

	/**
	 * 保存用户操作日志信息
	 * @param userLogs
	 */
	public void saveUserLogsinfo(Userlogs userLogs);
	
	/**
	 * 查询用户操作日志
	 * @return
	 */
	public List<Userlogs> queryUserLogs(Long userid, PageBean pageBean);
	
}
