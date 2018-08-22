package cn.lancel0t.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lancel0t.blog.domain.Category;
import cn.lancel0t.blog.repository.CategoryRepository;
import cn.lancel0t.blog.vo.CategoryResult;

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
	 * 获取分类和博客数量汇总
	 * 
	 * @return
	 */
	public List<CategoryResult> findAllWithBlogSize() {
		return categoryRepository.findAllWithBlogSize();
	}
}
