package com.xdl.system.service;

import java.util.List;

import com.xdl.system.entity.Cellinfo;

/**
 * 选择列接口
 */
public interface ICellinfoService {
	
	/**
	 * 保存/修改列名
	 * @param cellinfo
	 */
	public void saveOrUpdate(Cellinfo cellinfo);
	
	/**
	 * 删除用户的菜单选择列
	 * @param userid
	 * @param cellmenu
	 */
	public void deleteCellinfo(Long userid, String cellmenu);
	
	/**
	 * 查询用户的菜单列名
	 * @param userid
	 */
	public List<Cellinfo> queryCellinfo(Long userid, String cellmenu);

}
