package com.xdl.system.service;

import java.util.List;

import com.xdl.system.entity.Todaynote;


/**
 * 今日目标接口
 */
public interface ITodayNoteService {

	/**
	 * 增加/修改今日目标
	 * @param todaynote
	 */
	public void saveOrUpdate(Todaynote todaynote);
	
	/**
	 * 查询
	 * @param userid
	 * @return
	 */
	public List<Todaynote> queryTodaynote(Long userid);
	
}
