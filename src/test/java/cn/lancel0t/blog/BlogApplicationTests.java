package cn.lancel0t.blog;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.lancel0t.blog.domain.Tag;
import cn.lancel0t.blog.repository.BlogRepository;
import cn.lancel0t.blog.repository.CategoryRepository;
import cn.lancel0t.blog.repository.TagRepository;
import cn.lancel0t.blog.service.CategoryService;
import cn.lancel0t.blog.service.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TagRepository tagRepository;

	@Autowired
	CommentService commentService;

	@Test
	@Transactional
	public void initCategorys() {

		// categoryRepository.save(new Category("Category1"));
		// categoryRepository.save(new Category("Category2"));
		// categoryRepository.save(new Category("Category3"));
		//
		// categoryRepository.save(new Category("Category4"));
		// categoryRepository.save(new Category("Category5"));
		// categoryRepository.save(new Category("Category6"));
		//
		// categoryRepository.save(new Category("Category7"));
		// categoryRepository.save(new Category("Category8"));
		//
		// categoryRepository.save(new Category("Category9"));
		// categoryRepository.save(new Category("Category10"));
	}

	@Test
	@Transactional
	public void initTags() {
		
		tagRepository.deleteAll();
		List<Tag> tags = new ArrayList<Tag>();
		
		tags.add(new Tag("HTML"));
		tags.add(new Tag("CSS"));
		tags.add(new Tag("JavaScript"));
		tags.add(new Tag("jQuery"));
		tags.add(new Tag("AngularJS"));
		tags.add(new Tag("Bootstrap"));
		tags.add(new Tag("Android"));
		tags.add(new Tag("iOS"));
		tags.add(new Tag("Unity3D"));
		tags.add(new Tag("Python"));
		tags.add(new Tag("Apache"));
		tags.add(new Tag("Node.js"));
		tags.add(new Tag("ASP.NET MVC"));
		tags.add(new Tag("Entity Framework"));
		tags.add(new Tag("WPF"));
		tags.add(new Tag("Maven"));
		tags.add(new Tag("JSP"));
		tags.add(new Tag("Spring"));
		tags.add(new Tag("Spring MVC"));
		tags.add(new Tag("Spring Boot"));
		tags.add(new Tag("Struts2"));
		tags.add(new Tag("MyBatis"));
		tags.add(new Tag("Hibernate"));
		tags.add(new Tag("Lucene"));
		tags.add(new Tag("Go"));
		tags.add(new Tag("beego"));
		tags.add(new Tag("Hadoop"));
		tags.add(new Tag("Storm"));
		tags.add(new Tag("Kafka"));
		tags.add(new Tag("Zookeeper"));
		tags.add(new Tag("Hbase"));
		tags.add(new Tag("Hive"));
		tags.add(new Tag("Spark"));
		tags.add(new Tag("MySQL"));
		tags.add(new Tag("SQL Server"));
		tags.add(new Tag("Oracle"));
		tags.add(new Tag("SQL"));
		tags.add(new Tag("Redis"));
		tags.add(new Tag("MongoDB"));
		tags.add(new Tag("MyCat"));
		tags.add(new Tag("C"));
		tags.add(new Tag("C++"));
		tags.add(new Tag("Swift"));
		tags.add(new Tag("Java"));
		tags.add(new Tag("C#"));
		tags.add(new Tag("Git/Github"));
		tags.add(new Tag("SVN"));
		tags.add(new Tag("Linux"));
		tags.add(new Tag("CentOS"));
		tags.add(new Tag("Windows"));
		tags.add(new Tag("Mac OS"));
		tags.add(new Tag("Elasticsearch"));
		
		tagRepository.saveAll(tags);
	}

}
