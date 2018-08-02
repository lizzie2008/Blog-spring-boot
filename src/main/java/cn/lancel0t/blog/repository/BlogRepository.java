package cn.lancel0t.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.lancel0t.blog.domain.Blog;

/**
 * 博客 仓储方法
 * 
 * @author Work
 *
 */
public interface BlogRepository extends JpaRepository<Blog, String>, JpaSpecificationExecutor<Blog> {

	/**
	 * 根据标题查找
	 * 
	 * @param string
	 * @return
	 */
	Blog findByTitle(String string);

	/**
	 * 阅读量自增
	 * 
	 * @return
	 */
	@Modifying
	@Query(value = "update Blog set readSize = readSize+1 where id=:id")
	int increaseReadSize(@Param("id") String id);
	
	/** 
	 * 评论量自增
	 * 
	 * @return
	 */
	@Modifying
	@Query(value = "update Blog set commentSize = commentSize+1 where id=:id")
	int increaseCommentSize(@Param("id") String id);

	// @Query(value = "SELECT b FROM Blog b"
	// + " LEFT JOIN FETCH b.category c"
	// + " LEFT JOIN FETCH b.comments d"
	// + " LEFT JOIN FETCH b.votes v"
	// , countQuery = "SELECT count(b) FROM Blog b")
	// public Page<Blog> findAll(Pageable pageable);
}
