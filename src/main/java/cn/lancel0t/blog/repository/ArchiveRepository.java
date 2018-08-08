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

	/**
	 * 根据名称查找
	 * 
	 * @param name
	 * @return
	 */
	Archive findByName(String name);

	/**
	 * 倒序查询所有
	 * 
	 * @return
	 */
	List<Archive> findAllByOrderByNameDesc();
}
