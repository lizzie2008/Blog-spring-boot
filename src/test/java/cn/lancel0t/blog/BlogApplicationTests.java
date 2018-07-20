package cn.lancel0t.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.lancel0t.blog.domain.Blog;
import cn.lancel0t.blog.domain.Category;
import cn.lancel0t.blog.repository.BlogRepository;
import cn.lancel0t.blog.repository.CategoryRepository;
import cn.lancel0t.blog.service.CategoryService;
import cn.lancel0t.blog.vo.CategoryNode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	@Test
	@Transactional
	public void initCategory() {

		Category c1 = categoryRepository.save(new Category("Category1", 0L));
		Category c2 = categoryRepository.save(new Category("Category2", 0L));
		Category c3 = categoryRepository.save(new Category("Category3", 0L));

		categoryRepository.save(new Category("Category4", c1.getId()));
		categoryRepository.save(new Category("Category5", c1.getId()));
		categoryRepository.save(new Category("Category6", c1.getId()));

		categoryRepository.save(new Category("Category7", c2.getId()));
		categoryRepository.save(new Category("Category8", c2.getId()));

		categoryRepository.save(new Category("Category9", c3.getId()));
		categoryRepository.save(new Category("Category10", c3.getId()));
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
			blog.setCategory(new Category("category" + i, 0));
			blogRepository.save(blog);
		}
	}

	@Test
	@Transactional
	public void categoryServiceTest() {
		categoryService.getCategoryNodes();
	}

}
