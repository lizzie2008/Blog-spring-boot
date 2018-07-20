package cn.lancel0t.blog.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类树形结构
 * 
 * @author Work
 */
public class CategoryNode {

	private Long id; // 主键
	private String name; // 名称

	private List<CategoryNode> children = new ArrayList<CategoryNode>();

	public void addChild(CategoryNode child) {
		children.add(child);
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

	public List<CategoryNode> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryNode> children) {
		this.children = children;
	}

}
