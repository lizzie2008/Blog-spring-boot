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
}
