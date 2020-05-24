package com.xdl.system.service;

import java.util.List;

import com.xdl.publics.util.PageBean;
import com.xdl.system.entity.Dept;

/**
 * 部门管理接口类
 */
public interface IDeptService {

	/**
	 * 保存或者修改部门
	 * @param dept
	 */
	public void saveOrUpdate(Dept dept);
	
	/**
	 * 删除部门
	 * @param ids
	 */
	public void delete(String ids);
	
	/**
	 * 查询所有部门
	 * @return
	 */
	public List<Dept> queryDept(PageBean pageBean);
	
}
