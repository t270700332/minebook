package com.book.tzl.common.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		ModelAndView mv = new ModelAndView();
		FastJsonJsonView view = new FastJsonJsonView();
		Map<String, Object> attributes = new HashMap<>();
		if (ex instanceof UnauthorizedException) {
			attributes.put("code", "1000001");
			attributes.put("msg", "用户无权限");
		} else if (ex instanceof UnknownAccountException) {
			attributes.put("code", "1000002");
			attributes.put("msg", "用户名密码有误");
		} else if (ex instanceof IncorrectCredentialsException) {
			attributes.put("code", "1000002");
			attributes.put("msg", "用户名密码有误");
		} else if (ex instanceof LockedAccountException) {
			attributes.put("code", "1000003");
			attributes.put("msg", "账号已被锁定");
		} else {
			attributes.put("code", "1000004");
			attributes.put("msg", ex.getMessage());
		}

		view.setAttributesMap(attributes);
		mv.setView(view);
		return mv;
	}

}
