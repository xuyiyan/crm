package com.xdl.system.service;

import java.util.List;

import com.xdl.publics.util.PageBean;
import com.xdl.system.entity.Role;
import com.xdl.system.entity.Role_menu;

/**
 * 角色接口
 */
public interface IRoleService {
	
	/**
	 * 保存或者修改角色
	 * @param role
	 */
	public void saveOrUpdate(Role role);
	
	/**
	 * 保存角色-菜单对应关系
	 * @param roleMenu
	 */
	public void saveRoleMenu(Role_menu roleMenu);
	
	
	/**
	 * 删除角色
	 * @param ids
	 */
	public void delete(String ids);
	
	/**
	 * 根据部门ID，查询部门下的所有角色
	 * @param deptid
	 * @return
	 */
	public List<Role> queryRoleByDeptid(Long deptid);
	
	/**
	 * 修改角色对应的菜单，删除对应关系
	 * @param roleid
	 */
	public void deleteRoleMenu(Long roleid);
	
	/**
	 * 查询所有角色列表
	 * @param pageBean
	 * @return
	 */
	public List<Role> queryAllRole(PageBean pageBean);
	
	

}
