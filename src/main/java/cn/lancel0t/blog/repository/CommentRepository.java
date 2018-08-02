package cn.lancel0t.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.lancel0t.blog.domain.Comment;

/**
 * 评论 仓储方法
 * @author Work
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long> , JpaSpecificationExecutor<Comment>{

	
}
