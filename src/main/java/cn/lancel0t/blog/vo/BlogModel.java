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
	
	private String image; // 定义图片

	private String summary; // 摘要

	private String category; // 分类

	private String archive; // 归档

	private Date createTime; // 创建时间

	private Integer readSize = 0; // 访问量

	private Integer commentSize = 0; // 评论量

	private Integer likeSize = 0; // 点赞量

	// 根据blog实体构造
	public BlogModel(Blog blog) {
		this.setId(blog.getId());
		this.setTitle(blog.getTitle());
		this.setImage(blog.getImage());
		this.setSummary(blog.getSummary());
		this.setCategory(blog.getCategory().getName());
		this.setArchive(blog.getArchive().getName());
		this.setCreateTime(blog.getCreateTime());
		this.setReadSize(blog.getReadSize());
		this.setCommentSize(blog.getCommentSize());
		this.setLikeSize(blog.getLikeSize());
	}

	/**
	 * 转换为vo集合
	 * 
	 * @param blogs
	 * @return
	 */
	public static List<BlogModel> copyList(List<Blog> blogs) {
		List<BlogModel> blogModels = new ArrayList<>();
		for (Blog blog : blogs) {
			BlogModel blogModel = new BlogModel(blog);
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public Integer getLikeSize() {
		return likeSize;
	}

	public void setLikeSize(Integer likeSize) {
		this.likeSize = likeSize;
	}

	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

}
