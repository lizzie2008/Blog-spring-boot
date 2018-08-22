package cn.lancel0t.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.lancel0t.blog.service.TagService;

/*
 * 标签Controller
 */
@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private TagService tagService;

	/**
	 * 根据名称查找
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<?>> list(
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		return new ResponseEntity<List<?>>(tagService.findByNameContaining(name), HttpStatus.OK);
	}
}
