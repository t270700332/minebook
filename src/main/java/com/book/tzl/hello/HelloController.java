package com.book.tzl.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.tzl.common.Version;

@Controller
public class HelloController {

	@Autowired
	private Version version;

	@RequestMapping("/login")
	public String loign(Map<Object, String> map) {
		map.put("versionName", version.getName());
		return "login/login";
	}
}
