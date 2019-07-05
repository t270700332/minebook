package com.book.tzl.system.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.tzl.common.Version;
import com.book.tzl.common.handler.ExceptionHandler;
import com.book.tzl.system.user.domain.UserPojo;
import com.book.tzl.system.user.impl.UserServiceImpl;

import cn.hutool.core.util.ObjectUtil;

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

	@RequestMapping("/toindex")
	public Map<String, Object> index(String username, String password, HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		UserPojo user = userServiceImpl.findByUsername(username);
		Map<String, Object> attributes = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			if (!subject.isAuthenticated()) {
				subject.login(token);
			}
		} catch (Exception e) {
			ExceptionHandler.resolveException(request, response, handler, e, attributes);
		}

		return attributes;
	}

	@RequestMapping("/toregister")
	public String toRegister(UserPojo user) {
		return "login/register";
	}

	@RequestMapping("/register")
	@ResponseBody
	public HashMap<String, Object> register(UserPojo user) {
		HashMap<String, Object> result = new HashMap<>();
		Long num = userServiceImpl.createUser(user);
		if (num == 1) {
			result.put("code", 200);
			result.put("msg", "注册成功");
		} else {
			result.put("code", 400);
			result.put("msg", "注册失败");
		}
		return result;
	}

	@RequestMapping("/checkUsername")
	@ResponseBody
	public HashMap<String, Object> checkUsername(String username) {
		HashMap<String, Object> result = new HashMap<>();
		UserPojo user = userServiceImpl.findByUsername(username);
		if (ObjectUtil.isNull(user)) {
			result.put("code", 200);
			result.put("msg", "用户名可用");
		} else {
			result.put("code", 400);
			result.put("msg", "用户名已被占用");
		}
		return result;
	}
}
