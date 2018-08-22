package cn.lancel0t.blog.vo;

import java.io.Serializable;

/**
 * 归档结果
 * 
 * @author Work
 *
 */
public class ArchiveResult implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id; // 主键
	private String name; // 归档名称
	private int blogSize; // 博客数量
	
	public ArchiveResult()
	{
		
	}

	public ArchiveResult(Long id, String name, int blogSize) {
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
