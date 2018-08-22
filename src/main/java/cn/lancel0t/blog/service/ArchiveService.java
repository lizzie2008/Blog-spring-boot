package cn.lancel0t.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lancel0t.blog.domain.Archive;
import cn.lancel0t.blog.repository.ArchiveRepository;
import cn.lancel0t.blog.vo.ArchiveResult;

@Service
public class ArchiveService {

	@Autowired
	private ArchiveRepository archiveRepository;
	/**
	 * 获取所有归档
	 * 
	 * @return
	 */
	public List<Archive> findAll() {
		return archiveRepository.findAll();
	}
	
	/**
	 * 查找所有标签，并统计博客数量
	 * 
	 * @return
	 */
	public List<ArchiveResult> findAllWithBlogSize() {
		return archiveRepository.findAllWithBlogSize();
	}

}
