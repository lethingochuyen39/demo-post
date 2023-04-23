package com.aptech.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.blog.model.Post;
import com.aptech.blog.service.PostService;
import com.aptech.blog.service.PostServiceImpl;

@RestController
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;

	@GetMapping(path = "blogs/{blogId}/posts")
	public ResponseEntity<List<Post>> getAllPostByBlogId(
			@PathVariable(value = "blogId") int blogId) {
		List<Post> posts = postServiceImpl.findAllByBlogId(blogId);
		if (posts.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(posts);

	}

	@GetMapping(path = "posts/{postId}")
	public ResponseEntity<?> getPostById(@PathVariable(value = "postId") int postId) throws Exception {
		try {
			return ResponseEntity.ok(postServiceImpl.findById(postId));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping(path = "blogs/{blogId}/post")
	public ResponseEntity<?> add(@RequestBody Post post,
			@PathVariable(value = "blogId") int blogId) throws Exception {

		try {
			return new ResponseEntity<>(postServiceImpl.addPost(post, blogId), HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<?> update(@PathVariable("postId") int postId, @RequestBody Post post) {

		try {
			return ResponseEntity.ok(postServiceImpl.updatePost(post, postId));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable("postId") int postId) {
		postServiceImpl.deleteById(postId);
		return ResponseEntity.ok("delete post successfully");
	}

	@DeleteMapping("/blogs/{blogId}/posts")
	public ResponseEntity<?> deleteAllPostOfBlogs(
			@PathVariable(value = "blogId") int blogId) throws Exception {
		try {
			postServiceImpl.deleteAllPostsByBlogId(blogId);
			return new ResponseEntity<>("delete all post of blogs successfully", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping(path = "/posts/search{title}")
	public ResponseEntity<List<Post>> getPostByTitle(@PathVariable("title") String title) {
		return ResponseEntity.ok(postServiceImpl.findByTitle(title));
	}

}