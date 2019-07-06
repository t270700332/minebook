package com.book.tzl.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.ui.Model;

/**
 * 处理用户账号和密码的问题
 * 
 * @author SEELE
 *
 */
public class ExceptionHandler {

	public static Model resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex, Model model) {
		if (ex instanceof UnauthorizedException) {
			model.addAttribute("code", "1000001");
			model.addAttribute("msg", "用户无权限");
		} else if (ex instanceof UnknownAccountException) {
			model.addAttribute("code", "1000002");
			model.addAttribute("msg", "用户名或密码错误");
		} else if (ex instanceof IncorrectCredentialsException) {
			model.addAttribute("code", "1000002");
			model.addAttribute("msg", "用户名或密码错误");
		} else if (ex instanceof LockedAccountException) {
			model.addAttribute("code", "1000003");
			model.addAttribute("msg", "账号已被锁定");
		} else {
			model.addAttribute("code", "1000004");
			model.addAttribute("msg", ex.getMessage());
		}

		return model;
	}

}
