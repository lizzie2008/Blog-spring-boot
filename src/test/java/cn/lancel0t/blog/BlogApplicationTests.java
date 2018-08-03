package cn.lancel0t.blog;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.domain.Category;
import cn.lancel0t.blog.domain.Comment;
import cn.lancel0t.blog.repository.BlogRepository;
import cn.lancel0t.blog.repository.CategoryRepository;
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
	CommentService commentService;

	@Test
	@Transactional
	public void initCategory() {

		categoryRepository.save(new Category("Category1"));
		categoryRepository.save(new Category("Category2"));
		categoryRepository.save(new Category("Category3"));

		categoryRepository.save(new Category("Category4"));
		categoryRepository.save(new Category("Category5"));
		categoryRepository.save(new Category("Category6"));

		categoryRepository.save(new Category("Category7"));
		categoryRepository.save(new Category("Category8"));

		categoryRepository.save(new Category("Category9"));
		categoryRepository.save(new Category("Category10"));
	}

	@Test
	@Transactional
	public void contextLoads() {

		for (int i = 0; i < 10; i++) {
			Blog blog = new Blog();
			blog.setTitle("title" + i);
			blog.setImage("image" + i);
			blog.setSummary("summary" + i);
			blog.setContent("content" + i);
			blog.setCreateTime(new Date());
			blog.setCategory(new Category("category" + i));
			blogRepository.save(blog);
		}
	}

	@Test
	@Transactional
	public void commentServiceTest() {
		
		Blog blog = blogRepository.findByTitle("title0");

		Comment comment1 = new Comment();
		comment1.setBlog(blog);
		comment1.setNickName("张三");
		comment1.setWebSite("www.baidu.com");
		comment1.setBrowser("chrome");
		comment1.setContent("复杂sql查询支持吗？而且结果集不一定是实体。");
		commentService.save(comment1);

		Comment comment2 = new Comment();
		comment2.setBlog(blog);
		comment2.setNickName("李四");
		comment2.setBrowser("safari");
		comment2.setContent("边看你的内容一边在IDEA上看源码，看了一下午的源码，收获匪浅，真的很感谢你~~ 终生受用四个字不夸张");
		comment2.setReply(true);
		comment2.setParentCommentId(comment1.getId());
		commentService.save(comment2);
		
		Comment comment3 = new Comment();
		comment3.setBlog(blog);
		comment3.setNickName("王五");
		comment3.setBrowser("firefox");
		comment3.setContent("框架面向对象方式查询查询自带分页所有信息，包括页数，页码，总数，等等。");
		comment3.setReply(true);
		comment3.setParentCommentId(comment1.getId());
		commentService.save(comment3);
		
		Comment comment4 = new Comment();
		comment4.setBlog(blog);
		comment4.setNickName("神一样的存在");
		comment4.setBrowser("opera");
		comment4.setContent("推荐下，分库分表中间件 Sharding-JDBC 源码解析 17 篇：www.yunai.me/categories/Sharding-JDBC/?cnblog&601。");
		commentService.save(comment4);
		
		Comment comment5 = new Comment();
		comment5.setBlog(blog);
		comment5.setNickName("小杜比亚");
		comment5.setBrowser("msie");
		comment5.setContent("最近在使用Spring data Jpa，看大家都好评，那我就mark一下，以后能看懂的时候再来看。");
		commentService.save(comment5);
	}


}
