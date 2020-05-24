package com.xdl.system.service;

import com.xdl.system.entity.Action;


/**
 * 系统流程动作接口
 */
public interface IActionService {
	
	/**
	 * 保存动作流程
	 * @param action
	 */
	public void save(Action action);

}
