package cn.lancel0t.blog.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.lancel0t.blog.domain.Blog;

public class BlogSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id; // 主键

	private String title; // 标题

	private String category; // 分类

	private String tags; // 标签

	/**
	 * 转换为vo集合
	 * 
	 * @param blogs
	 * @return
	 */
	public static List<BlogSummary> copyList(List<Blog> blogs) {
		List<BlogSummary> blogSummarys = new ArrayList<>();
		for (Blog blog : blogs) {
			BlogSummary blogSummary = new BlogSummary();
			blogSummary.setId(blog.getId());
			blogSummary.setTitle(blog.getTitle());
			blogSummary.setCategory(blog.getCategory().getName());
			blogSummary.setTags(blog.getTags());
			blogSummarys.add(blogSummary);
		}
		return blogSummarys;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
