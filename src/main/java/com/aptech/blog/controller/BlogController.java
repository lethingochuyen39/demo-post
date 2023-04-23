package com.aptech.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.blog.model.Blog;
import com.aptech.blog.service.BlogServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000")
public class BlogController {

	@Autowired
	private BlogServiceImpl blogServiceImpl;

	@GetMapping(path = "/blogs")
	public ResponseEntity<List<Blog>> getAllBlogs() {
		return ResponseEntity.ok(blogServiceImpl.findAllBlogs());
	}

	@GetMapping(path = "/blogs/{blogId}")
	public ResponseEntity<?> getBlogById(@PathVariable("blogId") Integer blogId) {
		return ResponseEntity.ok(blogServiceImpl.findBlogById(blogId));
	}

	@PostMapping(path = "/blogs")
	public ResponseEntity<?> createBlog(@RequestBody Blog blog) {
		Blog newBlog = blogServiceImpl.addBlog(blog);
		return ResponseEntity.ok(newBlog);
	}

	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<?> updateBlog(@PathVariable("blogId") Integer blogId,
			@RequestBody Blog blog) {
		return ResponseEntity.ok(blogServiceImpl.updateBlog(blog, blogId));
	}

	@DeleteMapping("/blogs/{blogId}")
	public ResponseEntity<?> deleteBlogById(@PathVariable("blogId") Integer blogId) {
		blogServiceImpl.deleteBlogById(blogId);
		return ResponseEntity.ok("delete blog successfully");
	}

	@GetMapping(path = "/blogs/search{url}")
	public ResponseEntity<List<Blog>> getBlogsByUrl(@PathVariable("url") String url) {
		return ResponseEntity.ok(blogServiceImpl.findByUrl(url));
	}

}