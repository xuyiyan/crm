package com.xdl.publics.logs.controller;

import java.lang.reflect.Method;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xdl.publics.logs.entity.Userlogs;
import com.xdl.publics.logs.service.ILogsService;
import com.xdl.publics.util.CreatUserIpinfo;
import com.xdl.publics.util.CusAccessObjectUtil;
import com.xdl.publics.util.UserContext;
import com.xdl.system.entity.User;


/**
 * 切面
 */
@Controller
@Component
@Aspect
public class LogController {
	
	@Autowired
	private ILogsService logsService;
	
	public LogController() {
	
	}
	
	/**
	 * 统配注释
	 */
	@Pointcut("@annotation(com.hjcrm.publics.logs.controller.AfterMethodLog)")
	public void aftermethodCachePointcut() {
	
	}
	
	@Pointcut("@annotation(com.hjcrm.publics.logs.controller.BeforeMethodLog)")
	public void beforemethodCachePointcut() {
	
	}
	
	/**
	 * around记录切面数据
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@AfterReturning(pointcut = "aftermethodCachePointcut()",returning = "object")
	public Object around(JoinPoint  point) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = CusAccessObjectUtil.getIpAddress(request);
		String monthRemark = getMthodRemark(point,0);
		String monthName = point.getSignature().getName();
		String packages = point.getThis().getClass().getName();
		String username = "";
		Long userid = null;
		User user = UserContext.getLoginUser();
		if (user != null) {
			username = user.getUsername();
			userid  = user.getUserid();
		} else {
			username = "未知用户";
		}
		if (packages.indexOf("$$EnhancerByCGLIB$$") > -1) { //CGLIB动态生成
			try {
				packages = packages.substring(0, packages.indexOf("$$"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		Object[] method_param = null;
		Object object;
		try {
			method_param = point.getArgs();	//获取方法参数 
			object = point.getArgs();
		} catch (Exception e) {
			throw e;
		}
		
		Userlogs userLogs = CreatUserIpinfo.createUserLogsInfo(request, userid, username);
		if(userLogs==null){
			return object;
		}
		userLogs.setLogsuserip(ip);
		userLogs.setLogsinter("/" + monthName);
		if (userid == null) {
			userLogs.setLogsnote(monthRemark);
		}else{
			userLogs.setUserid(userid);
			userLogs.setUsername(username);
			userLogs.setLogsnote(monthRemark);
		}
		userLogs.setLogsparam(method_param[0].toString());
		userLogs.setLogstime(new Timestamp(System.currentTimeMillis()));
		
		logsService.saveUserLogsinfo(userLogs);
		
		return object;
	}
	
	@Before("beforemethodCachePointcut()")
	public Object beforearound(JoinPoint  point) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = CusAccessObjectUtil.getIpAddress(request);
		String monthRemark = getMthodRemark(point,1);
		String monthName = point.getSignature().getName();
		String packages = point.getThis().getClass().getName();
		String username = "";
		Long userid = null;
		User user = UserContext.getLoginUser();
		if (user != null) {
			username = user.getUsername();
			userid  = user.getUserid();
		} else {
			username = "未知用户";
		}
		if (packages.indexOf("$$EnhancerByCGLIB$$") > -1) { //CGLIB动态生成
			try {
				packages = packages.substring(0, packages.indexOf("$$"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		Object[] method_param = null;
		Object object;
		try {
			method_param = point.getArgs();	//获取方法参数 
			object = point.getArgs();
		} catch (Exception e) {
			throw e;
		}
		
		Userlogs userLogs = CreatUserIpinfo.createUserLogsInfo(request, userid, username);
		if(userLogs==null){
			return object;
		}
		userLogs.setLogsuserip(ip);
		userLogs.setLogsinter("/" + monthName);
		if (userid == null) {
			userLogs.setLogsnote(monthRemark);
		}else{
			userLogs.setUserid(userid);
			userLogs.setUsername(username);
			userLogs.setLogsnote(monthRemark);
		}
		userLogs.setLogsparam(method_param[0].toString());
		userLogs.setLogstime(new Timestamp(System.currentTimeMillis()));
		
		logsService.saveUserLogsinfo(userLogs);
		
		return object;
	}

	/**
	 * 获取方法的操作描述
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	public static String getMthodRemark(JoinPoint  joinPoint,Integer type) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] method = targetClass.getMethods();
		String methode = "";
		for (Method m : method) {
			if (m.getName().equals(methodName)) {
				Class[] tmpCs = m.getParameterTypes();
				if (tmpCs.length == arguments.length) {
					if (type == 0) {//after
						AfterMethodLog methodCache = m.getAnnotation(AfterMethodLog.class);
						if (methodCache != null) {
							methode = methodCache.remark();
						}
						break;
					}else if (type == 1){//before
						BeforeMethodLog methodCache = m.getAnnotation(BeforeMethodLog.class);
						if (methodCache != null) {
							methode = methodCache.remark();
						}
						break;
					}
				}
			}
		}
		return methode;
	}
	

}
