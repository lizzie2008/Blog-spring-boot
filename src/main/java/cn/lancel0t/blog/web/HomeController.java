package cn.lancel0t.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 主页Controller
 */
@Controller
public class HomeController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * html5Mode刷新界面跳转问题解决
	 */
	 @RequestMapping(value = "/**/{[path:[^\\.]*}")
	 public String redirect() {
	    // Forward to home page so that route is preserved.
	    return "forward:/";
	 }
}
