package cn.lancel0t.blog.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.repository.BlogRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	/**
	 * 根据id获得博客
	 * @param id
	 * @return
	 */
	public Blog getOne(String id) {
		return blogRepository.getOne(id);
	}

	/**
	 * 获取所有博客
	 * 
	 * @param id
	 * @return
	 */
	public Page<Blog> findAll(String title, String url, String category, String tag, Pageable pageable) {

		return blogRepository.findAll(new Specification<Blog>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (Long.class != query.getResultType()) {
					root.fetch("category", JoinType.LEFT);
				}

				// 根据title查询
				if (StringUtils.hasLength(title)) {
					predicates.add(cb.like(root.get("title"), "%" + title + "%"));
				}

				// 根据url查询
				if (StringUtils.hasLength(url)) {
					predicates.add(cb.like(root.get("url"), "%" + url + "%"));
				}

				// 根据tag查询
				if (StringUtils.hasLength(tag)) {
					predicates.add(cb.like(root.get("tags"), "%" + tag + "%"));
				}

				// 根据category查询
				if (StringUtils.hasLength(category)) {
					predicates.add(cb.equal(root.get("category").get("id"), category));
				}

				Predicate[] pre = new Predicate[predicates.size()];
				query.where(predicates.toArray(pre));
				return cb.and(predicates.toArray(pre));
			}
		}, pageable);
	}

	/**
	 * 新建博客或修改博客
	 * 
	 * @param blog
	 * @return
	 */
	@Transactional
	public Blog save(Blog blog) {
		return blogRepository.save(blog);
	}

	@Transactional
	public void deleteById(String id) {
		blogRepository.deleteById(id);
	}

}
