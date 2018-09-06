package cn.lancel0t.blog.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.domain.Blog.BlogType;
import cn.lancel0t.blog.domain.Comment;
import cn.lancel0t.blog.service.BlogService;
import cn.lancel0t.blog.vo.BlogModel;
import javassist.NotFoundException;

/*
 * 博客Controller
 */
@RestController
@RequestMapping("/api/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;

	/*****************************************************************/

	/**
	 * 博客列表（分页搜索排序）
	 * 
	 * @param model
	 * @param sort
	 * @param order
	 * @param page
	 * @param size
	 * @param title
	 * @param url
	 * @param category
	 * @param tag
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			@RequestParam(value = "order", required = false, defaultValue = "asc") String order,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "blogType", required = false, defaultValue = "NORMAL") BlogType blogType,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "category", required = false, defaultValue = "") String category,
			@RequestParam(value = "archive", required = false, defaultValue = "") String archive,
			@RequestParam(value = "tag", required = false, defaultValue = "") String tag) {

		// 排序与分页
		String[] splitSort = sort.split(",");
		String[] splitOrder = order.split(",");
		List<Order> list = new ArrayList<>();
		for (int i = 0; i < splitSort.length; i++) {
			Direction direction = splitOrder[i].equals("asc") ? Direction.ASC : Direction.DESC;
			list.add(new Order(direction, splitSort[i]));
		}

		Pageable pageable = PageRequest.of(page - 1, size, Sort.by(list));
		Page<Blog> pageBlog = blogService.list(blogType, title, category, archive, tag, pageable);

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
	 * 博客详情
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Blog detail(@PathVariable("id") String id) {
		blogService.increaseReadSize(id);
		return blogService.detail(id);
	}

	/**
	 * 专题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSubject", method = RequestMethod.GET)
	public Blog getSubject() {
		return blogService.getSubject();
	}

	/**
	 * 关于
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAbout", method = RequestMethod.GET)
	public Blog getAbout() {
		return blogService.getAbout();
	}

	/**
	 * 博客添加评论
	 * 
	 * @param id
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/{id}/comments", method = RequestMethod.POST)
	public Long addComment(@PathVariable("id") String id, @RequestBody Comment comment) {
		return blogService.addComment(id, comment);
	}

	/**
	 * 博客点赞
	 * 
	 * @param id
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/{id}/like", method = RequestMethod.POST)
	public void like(@PathVariable("id") String id) {
		blogService.increaseLikeSize(id);
	}
}
