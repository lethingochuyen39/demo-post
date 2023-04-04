package com.aptech.blog.controller;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.blog.model.Blog;
import com.aptech.blog.service.BlogService;

@RestController
@CrossOrigin("http://localhost:3000")
public class BlogController {

	@Autowired
	private BlogService service;

	@GetMapping(path = "/blogs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Page<Blog>> getAllBlogs(
			@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "${paging.pageNo}") int pageNo,
			@RequestParam(defaultValue = "${paging.pageSize}") int pageSize) {
		try {
			return new ResponseEntity<>(service.getAllPage(title, pageNo, pageSize), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path ="/blogs/{blogId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Blog> getBlogById(@PathVariable("blogId") Integer blogId) {

		if (service.getById(blogId) != null) {
			return new ResponseEntity<>(service.getById(blogId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/blogs/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId") Integer blogId) {
		if (blogId != null) {
			service.deleteById(blogId);
			return new ResponseEntity<String>("Delete blog success !", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Delete failed !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PostMapping(path = "/blogs", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> addBlog(@RequestBody Blog blog) {
		try {
			if (blog.getUrl().isEmpty()) {
				blog.setUrl("http:localhost:8080/blogs/" + blog.getBlogId());
			}
			service.add(blog);
			return new ResponseEntity<String>("Add blog success !", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Add failed !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/blogs/{blogId}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateBlog(@PathVariable("blogId") Integer blogId, @RequestBody Blog blog) {
		try {
			if (blogId != null) {
				if (blog.getUrl().isEmpty()) {
					blog.setUrl("http:localhost:8080/blogs/" + blogId);
				}
				service.update(blogId, blog);
				return new ResponseEntity<String>("Update blog success !", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("blogId cannot be empty !", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Update failed !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	// @GetMapping(path = "/blogs/getAllBlogs", produces = {MediaType.APPLICATION_JSON_VALUE })
	// public ResponseEntity<List<Blog>> getAllBlogs(@RequestParam(required = false)
	// String title) {
	// try {
	// return new ResponseEntity<>(service.find(title), HttpStatus.OK);
	// } catch (Exception e) {
	// return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	// }
	// }
}