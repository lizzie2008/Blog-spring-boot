package cn.lancel0t.blog.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.lancel0t.blog.domain.Archive;
import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.domain.BlogES;
import cn.lancel0t.blog.domain.Comment;
import cn.lancel0t.blog.domain.Tag;
import cn.lancel0t.blog.domain.Blog.BlogType;
import cn.lancel0t.blog.repository.ArchiveRepository;
import cn.lancel0t.blog.repository.BlogESRepository;
import cn.lancel0t.blog.repository.BlogRepository;
import cn.lancel0t.blog.repository.CommentRepository;
import javassist.NotFoundException;

@Service
public class BlogService {

	private final Logger logger = LoggerFactory.getLogger(BlogService.class);

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private BlogESRepository blogESRepository;

	@Autowired
	private ArchiveRepository archiveRepository;

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 获取所有博客
	 * 
	 * @param id
	 * @return
	 */
	public Page<Blog> list(BlogType blogType, String title, String category, String archive, String tag,
			Pageable pageable) {

		return blogRepository.findAll(new Specification<Blog>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				// 采取fetch join，一次访问相关数据
				if (Long.class != query.getResultType()) {
					root.fetch("category", JoinType.LEFT);
					root.fetch("archive", JoinType.LEFT);
				}

				// 根据title查询
				if (StringUtils.hasLength(title)) {
					predicates.add(cb.like(root.get("title"), "%" + title + "%"));
				}

				// 根据tag查询
				if (StringUtils.hasLength(tag)) {
					Join<Blog, Tag> join = root.join("tags", JoinType.INNER);
					predicates.add(cb.equal(join.get("id"), tag));
				}

				// 根据category查询
				if (StringUtils.hasLength(category)) {
					predicates.add(cb.equal(root.get("category").get("id"), category));
				}

				// 根据archive查询
				if (StringUtils.hasLength(archive)) {
					predicates.add(cb.equal(root.get("archive").get("id"), archive));
				}

				// 只查询普通类型
				predicates.add(cb.equal(root.get("blogType"), blogType));

				Predicate[] pre = new Predicate[predicates.size()];
				query.where(predicates.toArray(pre));
				return cb.and(predicates.toArray(pre));
			}
		}, pageable);
	}

	/**
	 * 博客详情
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	@Transactional
	public Blog detail(String id) {
		return blogRepository.getOne(id);
	}

	/**
	 * 主题
	 * 
	 * @return
	 */
	@Transactional
	public Blog getSubject() {
		return blogRepository.findByBlogType(BlogType.SUBJECT);
	}

	/**
	 * 关于
	 * 
	 * @return
	 */
	@Transactional
	public Blog getAbout() {
		return blogRepository.findByBlogType(BlogType.ABOUT);
	}

	/**
	 * 新建博客或修改博客
	 * 
	 * @param blog
	 * @return
	 */
	@Transactional
	public void save(Blog blog) {

		// 归档管理
		if (blog.getBlogType() == BlogType.NORMAL) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
			String dateString = formatter.format(blog.getCreateTime());
			Archive existArchive = archiveRepository.findByName(dateString);
			if (existArchive == null) {
				// 归档目录不存在，新建归档目录
				Archive archive = new Archive(dateString);
				archiveRepository.save(archive);
				blog.setArchive(archive);
			} else {
				// 归档目录已存在，使用已有的归档目录
				blog.setArchive(existArchive);
			}
		}

		// 保存博客对象
		blogRepository.save(blog);

		// 保存到elasticsearch,捕获异常，打印异常信息，不影响正常保存
		try {
			if (blog.getBlogType() == BlogType.NORMAL) {
				BlogES blogES = new BlogES(blog);
				blogESRepository.save(blogES);
			}
		} catch (Exception e) {
			logger.warn("save blog to elasticsearch failed.");
		}
	}

	@Transactional
	public void deleteById(String id) {
		blogRepository.deleteById(id);
	}

	/**
	 * 博客添加评论
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	@Transactional
	public Long addComment(String id, Comment comment) {
		// 评论数自增1
		blogRepository.increaseCommentSize(id);
		comment.setBlog(new Blog());
		comment.getBlog().setId(id);
		return commentRepository.save(comment).getId();
	}

	/**
	 * 阅读量自增
	 * 
	 * @param id
	 */
	@Transactional
	public void increaseReadSize(String id) {
		// 阅读量自增1
		blogRepository.increaseReadSize(id);
	}

	/**
	 * 点赞
	 * 
	 * @param id
	 */
	@Transactional
	public void increaseLikeSize(String id) {
		// 点赞数自增1
		blogRepository.increaseLikeSize(id);
	}
}
