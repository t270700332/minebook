package com.book.tzl.common.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;

/**
 * 处理用户账号和密码的问题
 * 
 * @author SEELE
 *
 */
public class ExceptionHandler {

	public static Map<String, Object> resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex, Map<String, Object> attributes) {
		if (ex instanceof UnauthorizedException) {
			attributes.put("code", "1000001");
			attributes.put("msg", "用户无权限");
		} else if (ex instanceof UnknownAccountException) {
			attributes.put("code", "1000002");
			attributes.put("msg", "用户名或密码错误");
		} else if (ex instanceof IncorrectCredentialsException) {
			attributes.put("code", "1000002");
			attributes.put("msg", "用户名或密码错误");
		} else if (ex instanceof LockedAccountException) {
			attributes.put("code", "1000003");
			attributes.put("msg", "账号已被锁定");
		} else {
			attributes.put("code", "1000004");
			attributes.put("msg", ex.getMessage());
		}

		return attributes;
	}

}
