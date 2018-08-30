package cn.lancel0t.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.lancel0t.blog.domain.Category;
import cn.lancel0t.blog.vo.CategoryResult;

/**
 * 分类 仓储方法
 * 
 * @author Work
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/**
	 * 查找所有分类，并统计博客数量
	 * 
	 * @return
	 */
	@Query("select new cn.lancel0t.blog.vo.CategoryResult(o.id,o.name,o.blogs.size) " +
			"from Category o " +
			"where o.blogs.size>0 " +
			"order by o.blogs.size desc")
	List<CategoryResult> findAllWithBlogSize();
}
