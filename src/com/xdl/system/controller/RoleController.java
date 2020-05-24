package com.xdl.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdl.publics.constants.JumpViewConstants;
import com.xdl.publics.constants.ReturnConstants;
import com.xdl.publics.logs.controller.AfterMethodLog;
import com.xdl.publics.logs.controller.BeforeMethodLog;
import com.xdl.publics.util.BaseController;
import com.xdl.publics.util.UserContext;
import com.xdl.system.entity.Menu;
import com.xdl.system.entity.Role;
import com.xdl.system.entity.Role_menu;
import com.xdl.system.service.IMenuService;
import com.xdl.system.service.IRoleService;

/**
 * 角色控制类
 */
@Controller
public class RoleController extends BaseController{
	
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IMenuService menuService;
	
	
	/**
	 * 跳转角色管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/roleMang.do",method = RequestMethod.GET)
	public String index(Model model){
		if (UserContext.getLoginUser() != null) {
			return JumpViewConstants.SYSTEM_ROLE_MANAGE;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	
	/**
	 * 新增保存/修改角色
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/role/addOrUpdateRole.do",method = RequestMethod.POST)
	public @ResponseBody String addOrUpdateRole(HttpServletRequest request,Role role){
		if (role != null) {
			roleService.saveOrUpdate(role);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 删除角色(支持批量，逗号隔开)
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/role/deleteRole.do",method = RequestMethod.POST)
	public @ResponseBody String deleteRole(HttpServletRequest request,String ids){
		if (ids != null && !"".equals(ids)) {
			roleService.delete(ids);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
	/**
	 * 查询加载所有菜单列表，并将有权限的菜单选中
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rolemenu/queryAllMenuAndSelected.do", method = RequestMethod.GET)
	public @ResponseBody String queryAllMenuAndSelected(HttpServletRequest request,Long roleid){
		if (roleid != null) {
			//查询所有菜单
			List<Menu> list = menuService.queryAllMenu();
			//查询角色拥有的权限菜单
			List<Menu> listRole = menuService.queryMenuByRoleid(roleid);
			if (list != null && list.size() > 0) {
				if ( listRole != null && listRole.size() > 0) {
					Long listmenuid = null;
					Long listRolemenuid = null;
					Long listchildrenmenuid = null;
					for (int i = 0; i < list.size(); i++) {
						listmenuid = list.get(i).getMenuid();//总菜单ID
						for (int j = 0; j < listRole.size(); j++) {
							listRolemenuid = listRole.get(j).getMenuid();//选中菜单ID
							if (listmenuid == listRolemenuid) {
								list.get(i).setSelected(true);
								listRolemenuid = null;
								break;
							} 
						}
						listmenuid = null;
					}
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getChildren() != null && list.get(i).getChildren().size() > 0) {
							 for (int j = 0; j < list.get(i).getChildren().size(); j++) {
								 listchildrenmenuid = list.get(i).getChildren().get(j).getMenuid();
								 for (int n = 0; n < listRole.size(); n++) {
										listRolemenuid = listRole.get(n).getMenuid();//选中菜单ID
										if (listchildrenmenuid == listRolemenuid) {
											list.get(i).getChildren().get(j).setSelected(true);
											listRolemenuid = null;
											break;
										} 
								}
								 listchildrenmenuid = null;
							}
						}
					}
				}
			}
			return json(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 给角色分配菜单权限
	 * @param request
	 * @param roleid
	 * @param menuid
	 * @return
	 */
	@RequestMapping(value = "/rolemenu/assignMenu.do",method = RequestMethod.POST)
	public @ResponseBody String assignMenu(HttpServletRequest request,Long roleid,String menuid){
		if (roleid != null) {
			//修改角色对应的菜单，删除对应关系
			roleService.deleteRoleMenu(roleid);
			//将新的关系数据保存在关系表中
			Role_menu roleMenu = new Role_menu();
			for (String id : menuid.split(",")) {
				roleMenu.setRoleid(roleid);
				roleMenu.setMenuid(new Long(id));
				roleService.saveRoleMenu(roleMenu);
			}
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
	/**
	 * 根据部门ID，查询部门下的所有角色
	 * @param request
	 * @param deptid
	 * @return
	 */
	@RequestMapping(value = "/role/queryRoleByDeptid.do",method = RequestMethod.GET)
	public @ResponseBody String queryRoleByDeptid(HttpServletRequest request,Long deptid){
		 if (deptid != null) {
			List<Role> list = roleService.queryRoleByDeptid(deptid);
			return json(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 查询所有角色列表（暂不支持分页）
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(value = "/role/queryAllRole.do",method = RequestMethod.GET)
	public @ResponseBody String queryAllRole(HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<Role> list = roleService.queryAllRole(processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	

}
