package cn.lancel0t.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lancel0t.blog.domain.Category;
import cn.lancel0t.blog.repository.CategoryRepository;
import cn.lancel0t.blog.vo.CategoryNode;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 获取所有分类
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	/**
	 * 获取分类菜单
	 */
	public List<CategoryNode> getCategoryNodes() {

		List<CategoryNode> nodes = new ArrayList<CategoryNode>();
		List<Category> categorys = categoryRepository.findAll();

		for (Category category : categorys) {
			// 如果是根菜单
			if (category.getParentId().equals(0L)) {
				nodes.add(GetOneCategoryNode(category, categorys));
			}
		}
		return nodes;
	}

	/**
	 * 递归构造分类菜单
	 * 
	 * @param category
	 * @param categorys
	 * @return
	 */
	private CategoryNode GetOneCategoryNode(Category category, List<Category> categorys) {

		CategoryNode node = new CategoryNode();
		node.setId(category.getId());
		node.setName(category.getName());

		for (Category item : categorys) {
			// 如果是当前元素是父节点
			if (item.getParentId().equals(category.getId())) {
				node.addChild(GetOneCategoryNode(item, categorys));
			}
		}

		return node;
	}
}
