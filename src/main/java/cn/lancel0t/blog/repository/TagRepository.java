package cn.lancel0t.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.lancel0t.blog.domain.Tag;
import cn.lancel0t.blog.vo.TagResult;

/**
 * 分类 仓储方法
 * 
 * @author Work
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

	/**
	 * 根据标签名称查找
	 * 
	 * @param name
	 * @return
	 */
	List<Tag> findByNameContaining(String name);

	/**
	 * 查找所有标签，并统计博客数量
	 * 
	 * @return
	 */
	@Query("select new cn.lancel0t.blog.vo.TagResult(o.id,o.name,o.blogs.size) " +
			"from Tag o " +
			"where o.blogs.size>0 " +
			"order by o.blogs.size desc")
	List<TagResult> findAllWithBlogSize();
}
