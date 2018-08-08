package cn.lancel0t.blog.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 分类 实体
 * 
 * @author Work
 */
@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 主键

	@NotEmpty
	@Size(max = 30)
	@Column(nullable = false, length = 30)
	private String name; // 分类名称

	private Integer blogSize = 0; // 博客数量

	public Category() {
	}

	public Category(String name) {
		this.name = name;
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

	public Integer getBlogSize() {
		return blogSize;
	}

	public void setBlogSize(Integer blogSize) {
		this.blogSize = blogSize;
	}
}
