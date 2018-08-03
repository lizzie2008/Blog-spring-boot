package cn.lancel0t.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.lancel0t.blog.domain.Archive;

/**
 * 归档 仓储方法
 * 
 * @author Work
 */
public interface ArchiveRepository extends JpaRepository<Archive, Long> {

	List<Archive> findByName(String name);
}
