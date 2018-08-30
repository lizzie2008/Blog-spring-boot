package cn.lancel0t.blog.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lancel0t.blog.domain.Archive;
import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.domain.Category;
import cn.lancel0t.blog.domain.Tag;
import cn.lancel0t.blog.service.ArchiveService;
import cn.lancel0t.blog.service.BlogService;
import cn.lancel0t.blog.service.CategoryService;
import javassist.NotFoundException;

/*
 * 管理后台Controller
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArchiveService archiveService;

	/**
	 * 绑定实体时，防止form表单提交的null值转成""
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	/**
	 * 默认跳转页
	 * 
	 * @return
	 */
	@RequestMapping
	public String admin() {
		return "redirect:/admin/blogs";
	}

	/**
	 * 博客列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/blogs")
	public ModelAndView blogList() {

		ModelAndView view = new ModelAndView("admin/bloglist");
		List<Category> categorys = categoryService.findAll();
		view.addObject("categorys", categorys);
		List<Archive> archives = archiveService.findAll();
		view.addObject("archives", archives);
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

		ModelAndView view = new ModelAndView("admin/blogdetail");
		Blog blog = blogService.detail(id);
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

		ModelAndView view = new ModelAndView("admin/blogedit");
		List<Category> categorys = categoryService.findAll();
		view.addObject("categorys", categorys);
		Blog blog = new Blog();
		blog.setCategory(new Category());
		blog.setArchive(new Archive());
		blog.setCreateTime(new Date());
		view.addObject("blog", blog);
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

		Blog blog = blogService.detail(id);
		if (blog == null)
			throw new NotFoundException("no blog founded!");
		// 标签处理
		List<String> tagNames = new ArrayList<>();
		for (Tag tag : blog.getTags()) {
			tagNames.add(tag.getName());
		}

		ModelAndView view = new ModelAndView("admin/blogedit");
		List<Category> categorys = categoryService.findAll();
		view.addObject("categorys", categorys);
		view.addObject("blog", blog);
		view.addObject("tagNames", String.join(",", tagNames));
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
	@ResponseBody
	public ResponseEntity<String> blogSave(@RequestBody Blog blog) {
		blogService.save(blog);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
}
