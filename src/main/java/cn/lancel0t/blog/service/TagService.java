package cn.lancel0t.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lancel0t.blog.repository.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	
	public List<?> findByNameContaining(String name) {
		return tagRepository.findByNameContaining(name);
	}
	
	public List<?> findAllWithBlogSize() {
		return tagRepository.findAllWithBlogSize();
	}
}
