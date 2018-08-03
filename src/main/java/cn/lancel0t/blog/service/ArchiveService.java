package cn.lancel0t.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lancel0t.blog.domain.Archive;
import cn.lancel0t.blog.repository.ArchiveRepository;

@Service
public class ArchiveService {

	@Autowired
	private ArchiveRepository archiveRepository;

	/**
	 * 获取所有分类
	 * 
	 * @return
	 */
	public List<Archive> findAll() {
		return archiveRepository.findAll();
	}
	
	
}
