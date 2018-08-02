package cn.lancel0t.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.service.BlogService;
import cn.lancel0t.blog.service.CategoryService;
import cn.lancel0t.blog.vo.CategoryNode;
import javassist.NotFoundException;

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

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;

	/**
	 * 博客列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/blogs")
	public ModelAndView blogList() {

		ModelAndView view = new ModelAndView("/admin/bloglist");
		List<CategoryNode> categoryNodes = categoryService.getCategoryNodes();
		view.addObject("categoryNodes", categoryNodes);
		return view;
	}

	/**
	 * 博客详情
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/blogs/{id}")
	public ModelAndView blogDetail(@PathVariable("id") String id) {

		ModelAndView view = new ModelAndView("/admin/blogdetail");
		Blog blog = blogService.detail(id, false);
		view.addObject("blog", blog);
		return view;
	}

	/**
	 * 新建博客
	 * 
	 * @return
	 */
	@GetMapping(value = "/blogs/edit")
	public ModelAndView blogAdd() {

		ModelAndView view = new ModelAndView("/admin/blogedit");
		List<CategoryNode> categoryNodes = categoryService.getCategoryNodes();
		view.addObject("categoryNodes", categoryNodes);
		view.addObject("blog", new Blog());
		return view;
	}

	/**
	 * 编辑博客
	 * 
	 * @return
	 * @throws NotFoundException
	 */
	@GetMapping(value = "/blogs/edit/{id}")
	public ModelAndView blogEidt(@PathVariable("id") String id) throws NotFoundException {

		Blog blog = blogService.detail(id, false);
		if (blog == null)
			throw new NotFoundException("no blog founded!");

		ModelAndView view = new ModelAndView("/admin/blogedit");
		List<CategoryNode> categoryNodes = categoryService.getCategoryNodes();
		view.addObject("categoryNodes", categoryNodes);
		view.addObject("blog", blog);
		return view;
	}

	/**
	 * 保存博客
	 * 
	 * @param username
	 * @param blog
	 * @return
	 */
	@PostMapping("/blogs/edit")
	public String blogSave(Blog blog) {
		blogService.save(blog);
		return "redirect:/admin/blogs";
	}
}
