package com.xdl.system.service;

import java.util.List;
import java.util.Map;

import com.xdl.publics.exception.BusinessException;
import com.xdl.publics.util.PageBean;
import com.xdl.system.entity.Menu;

/**
 * 菜单功能接口
 */
public interface IMenuService {

	/**
	 * 保存/修改菜单
	 * @param menu
	 */
	public void saveOrUpdate(Menu menu);
	
	/**
	 * 根据用户ID，查询用户权限的菜单
	 * @param userid
	 * @return
	 */
	public List<Menu> queryMenuByUserid(Long userid);
	
	/**
	 * 查询所有的分级别的菜单
	 * @param userid
	 * @return
	 */
	public List<Menu> queryMenuList(Long roleid);
	
	/**
	 * 根据角色id，查询角色权限的菜单
	 * @param roleid
	 * @return
	 */
	public List<Menu> queryMenuByRoleid(Long roleid);
	
	/**
	 * 获取所有菜单，不划分主子菜单
	 * @return
	 */
	public List<Menu> queryMenus();
	
	/**
	 * 删除菜单，如果存在下级菜单则不允许删除
	 * @param id
	 * @throws DaoException
	 */
	public void delete(String id) throws BusinessException;
	
	/**
	 * 查询加载全部菜单
	 */
	public List<Menu> queryAllMenu();
	
	/**
	 * 是否存在下级菜单
	 * @param id
	 * @return
	 */
	public boolean hasChild(Long id);
	
	/**
	 * 是否存在角色关联
	 * @param id
	 * @return
	 */
	public boolean hasLinked4Role(Long id);
	
	/**
	 * 根据主键查询菜单
	 * @param menuid
	 * @return
	 */
	public Menu queryById(Long menuid);
	
	
}
