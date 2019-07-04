package com.book.tzl.system.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.tzl.system.user.domain.UserPojo;
import com.book.tzl.system.user.mapper.UserMapper;
import com.book.tzl.system.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserPojo> findAll() {
		return userMapper.findAll();
	}

	@Override
	public UserPojo findByUsername(String userName) {
		return userMapper.findByUsername(userName);
	}

}
