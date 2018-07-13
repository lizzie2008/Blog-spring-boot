package cn.lancel0t.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 管理后台Controller
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping
	public String admin() {
		return "redirect:/admin/blogs";
	}
}
