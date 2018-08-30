package cn.lancel0t.blog.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.lancel0t.blog.domain.BlogES;

/**
 * 博客Elastic Search 仓储方法
 * 
 * @author Work
 *
 */
public interface BlogESRepository extends ElasticsearchRepository<BlogES, String> {

}
