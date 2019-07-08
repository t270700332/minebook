package com.book.tzl.common.handler;

import java.util.HashMap;
import java.util.Map;

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

	public static Map<String, Object> resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex, Model model) {
		Map<String, Object> result = new HashMap<>();
		if (ex instanceof UnauthorizedException) {
			result.put("code", "1000001");
			result.put("msg", "用户无权限");
		} else if (ex instanceof UnknownAccountException) {
			result.put("code", "1000002");
			result.put("msg", "用户名或密码错误");
		} else if (ex instanceof IncorrectCredentialsException) {
			result.put("code", "1000002");
			result.put("msg", "用户名或密码错误");
		} else if (ex instanceof LockedAccountException) {
			result.put("code", "1000003");
			result.put("msg", "账号已被锁定");
		} else {
			result.put("code", "1000004");
			result.put("msg", ex.getMessage());
		}

		return result;
	}

}
