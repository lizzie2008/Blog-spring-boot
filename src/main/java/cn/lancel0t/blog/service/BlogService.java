package cn.lancel0t.blog.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import cn.lancel0t.blog.repository.ArchiveRepository;
import cn.lancel0t.blog.repository.BlogESRepository;
import cn.lancel0t.blog.repository.BlogRepository;
import cn.lancel0t.blog.repository.CommentRepository;
import javassist.NotFoundException;

@Service
public class BlogService {

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
	public Page<Blog> list(String title, String category, String archive, String tag,
			Pageable pageable) {

		return blogRepository.findAll(new Specification<Blog>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

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
					predicates.add(cb.like(root.get("tags"), "%" + tag + "%"));
				}

				// 根据category查询
				if (StringUtils.hasLength(category)) {
					predicates.add(cb.equal(root.get("category").get("id"), category));
				}

				// 根据archive查询
				if (StringUtils.hasLength(archive)) {
					predicates.add(cb.equal(root.get("archive").get("id"), archive));
				}

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
	public Blog detail(String id, boolean needIncreaseReadSize) {
		if (needIncreaseReadSize) {
			// 每访问一次，阅读量自增1
			blogRepository.increaseReadSize(id);
		}
		return blogRepository.getOne(id);
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

		// 新建
		if (blog.getId() == null) {

			blogRepository.save(blog);
			// 新分类统计+1
			blog.getCategory().setBlogSize(blog.getCategory().getBlogSize() + 1);
			blog.getArchive().setBlogSize(blog.getArchive().getBlogSize() + 1);
		}
		// 更新
		else {

			Blog originBlog = blogRepository.findById(blog.getId()).get();

			originBlog.setId(blog.getId());
			originBlog.setTitle(blog.getTitle());
			originBlog.setImage(blog.getImage());
			originBlog.setSummary(blog.getSummary());
			originBlog.setContent(blog.getContent());
			originBlog.setCreateTime(blog.getCreateTime());

			// 原分类统计-1,新分类统计+1
			originBlog.getCategory().setBlogSize(originBlog.getCategory().getBlogSize() - 1);
			blog.getCategory().setBlogSize(blog.getCategory().getBlogSize() + 1);
			originBlog.setCategory(blog.getCategory());

			// 原归档统计-1,新归档统计+1
			originBlog.getArchive().setBlogSize(originBlog.getArchive().getBlogSize() - 1);
			blog.getArchive().setBlogSize(blog.getArchive().getBlogSize() + 1);
			originBlog.setArchive(blog.getArchive());
		}

		//保存到elasticsearch,捕获异常，打印异常信息，不影响正常保存
		try {
			BlogES blogES = new BlogES(blog);
			blogESRepository.save(blogES);
		} catch (Exception e) {
			e.printStackTrace();
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

}
