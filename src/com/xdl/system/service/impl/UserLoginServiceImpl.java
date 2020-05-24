package com.xdl.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdl.publics.dao.IDataAccess;
import com.xdl.publics.util.MD5Tools;
import com.xdl.system.entity.User;
import com.xdl.system.service.IUserLoginService;

/**
 * CRM系统用户接口实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserLoginServiceImpl implements IUserLoginService {
	
	@Autowired
	private IDataAccess<User> userDao;

	/**
	 *  验证用户手机号/邮箱是否存在
	 */
	
	public User authLoginNameIsExist(String email,String phone) {
		List<User> list = userDao.queryByStatment("authLoginNameIsExist", email, null);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询密码是否匹配。
	 */
	
	public boolean isPasswordMatch(Long userid, String password) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("userid", userid);
		param.put("password", MD5Tools.encode(password));
//		param.put("password", password);
		List<User> result = userDao.queryByStatment("isPasswordMatch", param, null);
		if(result == null || result.size() == 0)
			return false;
		return true;
	}

}
