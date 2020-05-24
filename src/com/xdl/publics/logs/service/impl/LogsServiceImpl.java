package com.xdl.publics.logs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdl.publics.dao.IDataAccess;
import com.xdl.publics.logs.entity.Userlogs;
import com.xdl.publics.logs.service.ILogsService;
import com.xdl.publics.util.PageBean;


/**
 * 日志接口实现类
 * @author likang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LogsServiceImpl implements ILogsService{
	
	@Autowired
	private IDataAccess<Userlogs> userLogsDao;
	

	/**
	 * 保持用户操作日志信息
	 */
	public void saveUserLogsinfo(Userlogs userLogs) {
		userLogsDao.insert(userLogs);
	}

	/**
	 * 查询用户操作日志
	 */
	public List<Userlogs> queryUserLogs(Long userid,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userid", userid);
		List<Userlogs> list = userLogsDao.queryByStatment("queryUserLogs", param, pageBean);
		return list;
		
	}
	
 
	
}
