package cn.lancel0t.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.domain.Blog.BlogType;
import cn.lancel0t.blog.domain.BlogES;
import cn.lancel0t.blog.repository.BlogESRepository;

@Service
public class BlogESService {

	private final Logger logger = LoggerFactory.getLogger(BlogESService.class);

	@Autowired
	private BlogESRepository blogESRepository;

	/**
	 * elasticsearch 保存博客
	 * 
	 * @param id
	 */
	@Transactional
	public void save(Blog blog) {
		// 捕获异常，打印异常信息，不影响正常保存
		try {
			if (blog.getBlogType() == BlogType.NORMAL) {
				BlogES blogES = new BlogES(blog);
				blogESRepository.save(blogES);
			}
		} catch (Exception e) {
			logger.warn("save blog to elasticsearch failed.");
		}
	}
}
