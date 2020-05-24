package com.xdl.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdl.system.entity.Action;
import com.xdl.system.service.IActionService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActionServiceImpl implements IActionService {

	
	public void save(Action action) {
		// TODO Auto-generated method stub
		
	}

}
