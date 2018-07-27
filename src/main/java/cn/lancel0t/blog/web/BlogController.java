package cn.lancel0t.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.service.BlogService;
import cn.lancel0t.blog.vo.BlogModel;

/*
 * 管理后台Controller
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

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
	public String getBlogs(Model model,
			@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			@RequestParam(value = "order", required = false, defaultValue = "asc") String order,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "url", required = false, defaultValue = "") String url,
			@RequestParam(value = "category", required = false, defaultValue = "") String category,
			@RequestParam(value = "tag", required = false, defaultValue = "") String tag) {

		// 排序与分页
		Direction direction = order.equals("asc") ? Direction.ASC : Direction.DESC;
		Pageable pageable = PageRequest.of(page - 1, size, new Sort(direction, sort));
		Page<Blog> pageBlog = blogService.findAll(title, url, category, tag, pageable);

		// 转换为vo集合
		List<BlogModel> rows = BlogModel.copyList(pageBlog.getContent());
		long total = pageBlog.getTotalElements();

		// 返回bootstrap-table指定json格式
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		return result.toJSONString();
	}

	/**
	 * 根据id获取博客
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Blog getBlog(@PathVariable("id") String id) {
		return blogService.getOne(id);
	}
}
