package cn.lancel0t.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.lancel0t.blog.domain.Archive;
import cn.lancel0t.blog.vo.ArchiveResult;

/**
 * 归档 仓储方法
 * 
 * @author Work
 */
public interface ArchiveRepository extends JpaRepository<Archive, Long> {

	/**
	 * 根据名称查找
	 * 
	 * @param name
	 * @return
	 */
	Archive findByName(String name);

	/**
	 * 查找所有标签，并统计博客数量
	 * 
	 * @return
	 */
	@Query("select new cn.lancel0t.blog.vo.ArchiveResult(o.id,o.name,o.blogs.size) " +
			"from Archive o " +
			"order by o.name desc")
	List<ArchiveResult> findAllWithBlogSize();

}
