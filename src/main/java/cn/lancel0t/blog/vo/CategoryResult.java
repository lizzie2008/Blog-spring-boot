package cn.lancel0t.blog.vo;

import java.io.Serializable;

/**
 * 分类结果
 * 
 * @author Work
 *
 */
public class CategoryResult implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id; // 主键
	private String name; // 分类名称
	private int blogSize; // 博客数量
	
	public CategoryResult()
	{
		
	}

	public CategoryResult(Long id, String name, int blogSize) {
		this.id = id;
		this.name = name;
		this.blogSize = blogSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBlogSize() {
		return blogSize;
	}

	public void setBlogSize(int blogSize) {
		this.blogSize = blogSize;
	}
	
	
}
