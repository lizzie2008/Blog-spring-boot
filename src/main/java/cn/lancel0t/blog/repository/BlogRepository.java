package cn.lancel0t.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.lancel0t.blog.domain.Blog;

/**
 * 博客 仓储方法
 * @author Work
 *
 */
public interface BlogRepository extends JpaRepository<Blog, String> , JpaSpecificationExecutor<Blog>{

//	@Query(value = "SELECT b FROM Blog b"
//			+ " LEFT JOIN FETCH b.category c"
//			+ " LEFT JOIN FETCH b.comments d"
//			+ " LEFT JOIN FETCH b.votes v"
//			, countQuery = "SELECT count(b) FROM Blog b")
//	public Page<Blog> findAll(Pageable pageable);
}
