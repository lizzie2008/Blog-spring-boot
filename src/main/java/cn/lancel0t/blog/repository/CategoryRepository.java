package cn.lancel0t.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.lancel0t.blog.domain.Category;

/**
 * 分类 仓储方法
 * @author Work
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
