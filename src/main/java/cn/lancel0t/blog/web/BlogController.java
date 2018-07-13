package cn.lancel0t.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.domain.Category;
import cn.lancel0t.blog.service.BlogService;
import cn.lancel0t.blog.service.CategoryService;
import cn.lancel0t.blog.vo.BlogSummary;
import cn.lancel0t.blog.vo.CategoryNode;
import javassist.NotFoundException;

/*
 * 管理后台Controller
 */
@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;

	/**
	 * 博客列表
	 * 
	 * @return
	 */
	@RequestMapping
	public ModelAndView blogList() {

		ModelAndView view = new ModelAndView("/admin/bloglist");
		List<CategoryNode> categoryNodes = categoryService.getCategoryNodes();
		view.addObject("categoryNodes", categoryNodes);
		return view;
	}

	/**
	 * 博客详情
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ModelAndView blogDetail(@PathVariable("id") String id) {

		ModelAndView view = new ModelAndView("/admin/blogdetail");
		Blog blog = blogService.getOne(id);
		view.addObject("blog", blog);
		return view;
	}

	/**
	 * 新建博客
	 * 
	 * @return
	 */
	@GetMapping(value = "/edit")
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
	@GetMapping(value = "/edit/{id}")
	public ModelAndView blogEidt(@PathVariable("id") String id) throws NotFoundException {

		Blog blog = blogService.getOne(id);
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
	@PostMapping("/edit")
	public String blogSave(Blog blog) {
		blogService.save(blog);
		return "redirect:/admin/blogs";
	}

	/*****************************************************************/

	/**
	 * 获取博客（分页搜索排序）
	 * 
	 * @param model
	 * @param sort
	 * @param order
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public String getBlogs(Model model,
			@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			@RequestParam(value = "order", required = false, defaultValue = "asc") String order,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "url", required = false, defaultValue = "") String url,
			@RequestParam(value = "category", required = false, defaultValue = "") String category,
			@RequestParam(value = "tag", required = false, defaultValue = "") String tag) {

		// 排序与分页
		Direction direction = order.equals("asc") ? Direction.ASC : Direction.DESC;
		Pageable pageable = PageRequest.of(page, size, new Sort(direction, sort));
		Page<Blog> pageBlog = blogService.findAll(title, url, category, tag, pageable);

		// 转换为vo集合
		List<BlogSummary> rows = BlogSummary.copyList(pageBlog.getContent());
		long total = pageBlog.getTotalElements();

		// 返回bootstrap-table指定json格式
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		return result.toJSONString();
	}
}
