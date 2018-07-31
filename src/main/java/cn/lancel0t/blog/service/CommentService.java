package cn.lancel0t.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lancel0t.blog.domain.Comment;
import cn.lancel0t.blog.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 保存
	 * @param comment
	 * @return
	 */
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	

}
