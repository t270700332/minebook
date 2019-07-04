package com.book.tzl.system.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.tzl.common.Version;
import com.book.tzl.system.user.domain.UserPojo;
import com.book.tzl.system.user.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private Version version;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/login")
	public String loign(Map<Object, String> map) {
		List<UserPojo> list = userServiceImpl.findAll();
		map.put("versionName", version.getName());
		return "login/login";
	}

	@RequestMapping("/register")
	public String register(Map<Object, String> map) {
		return "login/register";
	}
}
