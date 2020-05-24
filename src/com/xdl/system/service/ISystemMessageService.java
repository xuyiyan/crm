package com.xdl.system.service;

import java.sql.Timestamp;
import java.util.List;

import com.xdl.publics.util.PageBean;
import com.xdl.system.entity.Systemmessage;

/**
 * 系统消息接口
 */
public interface ISystemMessageService {

	/**
	 * 增加或者修改消息
	 * @param message
	 */
	public void saveOrUpdate(Systemmessage message);
	
	/**
	 * 查询最近一条发布信息
	 * @return
	 */
	public List<Systemmessage> querySystemmessageSend();
	
	/**
	 * 查询所有系统消息
	 * @return
	 */
	public List<Systemmessage> querySystemmessages(PageBean pageBean);
	
	/**
	 * 发布或者撤回消息
	 * @param systemmessageId
	 * @param issend
	 */
	public void sendMessage(String systemmessageId, String issend, Timestamp send_time);
	
}
