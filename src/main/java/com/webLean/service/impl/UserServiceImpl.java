package com.webLean.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webLean.dao.userTMapper;
import com.webLean.domain.userT;
import com.webLean.service.IUserService;

@Service("userService")  
public class UserServiceImpl implements IUserService {  
	@Autowired  
  private userTMapper mapper;

	@Override
	public userT getUserById(int userId) {
		userT  t = mapper.selectByPrimaryKey(userId);
		System.out.println(t.getUserName());
		return t;
	}
}
    
