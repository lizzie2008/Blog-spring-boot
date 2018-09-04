package cn.lancel0t.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.domain.Blog.BlogType;

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
	 * 根据博客类型查找
	 * 
	 * @param blogType
	 * @return
	 */
	Blog findByBlogType(BlogType blogType);

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

	/**
	 * 点赞量自增
	 * 
	 * @return
	 */
	@Modifying
	@Query(value = "update Blog set likeSize = likeSize+1 where id=:id")
	int increaseLikeSize(@Param("id") String id);

}
