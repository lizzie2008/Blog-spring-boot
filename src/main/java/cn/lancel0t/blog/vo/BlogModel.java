package cn.lancel0t.blog.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.lancel0t.blog.domain.Blog;

public class BlogModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id; // 主键

	private String title; // 标题
	
	private String summary; // 摘要

	private String category; // 分类

	private String tags; // 标签

	private Date createTime; // 创建时间

	private Integer readSize = 0; // 访问量、阅读量

	private Integer commentSize = 0; // 评论量

	private Integer voteSize = 0; // 点赞量
	/**
	 * 转换为vo集合
	 * 
	 * @param blogs
	 * @return
	 */
	public static List<BlogModel> copyList(List<Blog> blogs) {
		List<BlogModel> blogModels = new ArrayList<>();
		for (Blog blog : blogs) {
			BlogModel blogModel = new BlogModel();
			blogModel.setId(blog.getId());
			blogModel.setTitle(blog.getTitle());
			blogModel.setSummary(blog.getSummary());
			blogModel.setCategory(blog.getCategory().getName());
			blogModel.setTags(blog.getTags());
			blogModel.setCreateTime(blog.getCreateTime());
			blogModel.setReadSize(blog.getReadSize());
			blogModel.setCommentSize(blog.getCommentSize());
			blogModel.setVoteSize(blog.getVoteSize());
			blogModels.add(blogModel);
		}
		return blogModels;
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
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getReadSize() {
		return readSize;
	}

	public void setReadSize(Integer readSize) {
		this.readSize = readSize;
	}

	public Integer getCommentSize() {
		return commentSize;
	}

	public void setCommentSize(Integer commentSize) {
		this.commentSize = commentSize;
	}

	public Integer getVoteSize() {
		return voteSize;
	}

	public void setVoteSize(Integer voteSize) {
		this.voteSize = voteSize;
	}


}
