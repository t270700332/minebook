package com.book.tzl.system.user.service;

import java.util.List;

import com.book.tzl.system.user.domain.UserPojo;

public interface UserService {

	public List<UserPojo> findAll();
}