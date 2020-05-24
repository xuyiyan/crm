package com.xdl.system.controller;

import java.sql.Timestamp;
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
import com.xdl.publics.util.BaseController;
import com.xdl.publics.util.UserContext;
import com.xdl.system.entity.Systemmessage;
import com.xdl.system.service.ISystemMessageService;

/**
 * 系统消息
 */
@Controller
public class SystemMessageController extends BaseController{
	
	@Autowired
	private ISystemMessageService messageService;
	
	
	
	
	/**
	 * 跳转消息管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/systemMessage.do",method = RequestMethod.GET)
	public String index(Model model){
		if (UserContext.getLoginUser() != null) {
			return JumpViewConstants.SYSTEM_MESSAGE;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	
	
	/**
	 * 增加或者修改消息
	 * @param request
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/system/saveOrUpdateMessage.do",method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateMessage(HttpServletRequest request,Systemmessage message){
		if (message != null) {
			messageService.saveOrUpdate(message);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 查询最近一条发布信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/system/querySystemmessageSend.do",method = RequestMethod.GET)
	public @ResponseBody String querySystemmessageSend(HttpServletRequest request){
		List<Systemmessage> list = messageService.querySystemmessageSend();
		if (list != null && list.size() > 0) {
			return jsonToPage(list.get(0));
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 查询所有系统消息
	 * @return
	 */
	@RequestMapping(value = "/system/querySystemmessages.do",method = RequestMethod.GET)
	public @ResponseBody String querySystemmessages(HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<Systemmessage> list = messageService.querySystemmessages(processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	/**
	 * 发布或者撤回消息
	 * @param systemmessageId
	 * @param issend
	 */
	@RequestMapping(value = "/system/sendMessage.do",method = RequestMethod.GET)
	public @ResponseBody String sendMessage(HttpServletRequest request,String systemmessageId,String issend){
		if (systemmessageId != null && !"".equals(systemmessageId) && issend != null && !"".equals(issend.trim())) {
			 messageService.sendMessage(systemmessageId,issend,new Timestamp(System.currentTimeMillis()));
			 return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
	
	
	
	
	
	

}
