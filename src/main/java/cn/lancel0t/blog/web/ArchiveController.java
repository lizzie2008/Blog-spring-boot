package cn.lancel0t.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lancel0t.blog.service.ArchiveService;

/*
 * 归档Controller
 */
@RestController
@RequestMapping("/api/archives")
public class ArchiveController {

	@Autowired
	private ArchiveService archiveService;

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return new ResponseEntity<List<?>>(archiveService.findAllWithBlogSize(), HttpStatus.OK);
	}
}
