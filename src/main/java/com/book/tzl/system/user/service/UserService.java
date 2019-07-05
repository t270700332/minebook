package com.book.tzl.system.user.service;

import java.util.List;

import com.book.tzl.system.user.domain.UserPojo;

public interface UserService {

	public List<UserPojo> findAll();

	public UserPojo findByUsername(String userName);

	/**
	 * 创建用户
	 * 
	 * @param user
	 * @return
	 */
	Long createUser(UserPojo user);
}
