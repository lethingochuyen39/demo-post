package com.aptech.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.blog.model.Blog;
import com.aptech.blog.model.Post;
import com.aptech.blog.repository.BlogRepository;
import com.aptech.blog.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Blog> findAllBlogs() {
		return blogRepository.findAll();
	}

	@Override
	public Optional<Blog> findBlogById(int id) {
		Optional<Blog> blog = blogRepository.findById(id);
		return blog;
	}

	// tất cả các thao tác xóa được thực hiện trong một transaction
	@Transactional
	@Override
	public Blog addBlog(Blog blog) {
		List<Post> posts = new ArrayList<>(blog.getPosts());

		if (posts != null && !posts.isEmpty()) {
			for (Post post : posts) {
				post.setBlog(blog);
			}
			blog.setPosts(posts);
		}

		Blog savedBlog = blogRepository.save(blog);

		return savedBlog;
	}

	@Transactional
	@Override
	public Blog updateBlog(Blog updatedBlog, int blogId) {
		Blog blog = blogRepository.findById(blogId).orElse(null);
		if (blog != null) {
			blog.setUrl(updatedBlog.getUrl());
			blog.setRating(updatedBlog.getRating());
			Blog savedBlog = blogRepository.save(blog);
			return savedBlog;
		}
		return null;
	}

	@Transactional
	@Override
	public void deleteBlogById(int id) {
		Optional<Blog> blogOptional = blogRepository.findById(id);
		Blog blog = blogOptional.get();
		List<Post> posts = postRepository.findByBlogBlogId(blog.getBlogId());
		postRepository.deleteAll(posts);
		blogRepository.deleteById(id);
	}
	// @Transactional
	// @Override
	// public Blog updateBlog(Blog updatedBlog, int blogId) throws Exception {
	// Blog blog = blogRepository.findById(blogId).orElse(null);
	// if (blog == null) {
	// return null;
	// }
	// // update blog's properties
	// blog.setUrl(updatedBlog.getUrl());
	// blog.setRating(updatedBlog.getRating());
	// List<Post> updatedPosts = updatedBlog.getPosts();
	// // if no post is updated, return the updated blog directly
	// if (updatedPosts == null || updatedPosts.isEmpty()) {
	// return blogRepository.save(blog);
	// }
	// // use a set to keep track of updated post IDs
	// Set<Integer> updatedPostIds = new HashSet<>();
	// // loop through updated posts
	// for (Post updatedPost : updatedPosts) {
	// Integer postId = updatedPost.getPostId();
	// // if post ID is null or not found in the current blog's posts, create a new
	// // post
	// if (postId == null || !blog.containsPostWithId(postId)) {
	// Post newPost = new Post();
	// newPost.setTitle(updatedPost.getTitle());
	// newPost.setContent(updatedPost.getContent());
	// newPost.setBlog(blog);
	// blog.getPosts().add(newPost);
	// } else { // otherwise, update the existing post
	// Post post = blog.getPostWithId(postId);
	// if (post != null) {
	// post.setTitle(updatedPost.getTitle());
	// post.setContent(updatedPost.getContent());
	// updatedPostIds.add(postId);
	// }
	// }
	// }
	// // remove posts that are not in the updated posts set
	// blog.getPosts().removeIf(post -> !updatedPostIds.contains(post.getPostId()));
	// // save and return the updated blog
	// return blogRepository.save(blog);
	// }

	@Override
	public List<Blog> findByUrl(String url) {
		return blogRepository.findByUrlContaining(url);
	}

}