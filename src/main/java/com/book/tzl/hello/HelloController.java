package com.book.tzl.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/login")
	public String loign() {
		return "login/login";
	}
}
