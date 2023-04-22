package com.aptech.blog.service;

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
public class BlogServiceImpl implements BlogService<Blog> {

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

	@Override
	public Blog addBlog(Blog blog) {

		Blog savedBlog = blogRepository.save(blog);
		List<Post> posts = blog.getPosts();
		if (posts != null && !posts.isEmpty()) {
			for (Post post : posts) {
				post.setBlog(savedBlog);
				postRepository.save(post);
			}
		}
		return savedBlog;
	}

	@Override
	public Blog updateBlog(Blog blog, int id) {
		Optional<Blog> data = blogRepository.findById(id);

		Blog _blog = data.get();
		_blog.setUrl(blog.getUrl()).setRating(blog.getRating()).setPosts(blog.getPosts());
		return blogRepository.save(_blog);

	}

	// tất cả các thao tác xóa được thực hiện trong một transaction
	@Transactional
	@Override
	public void deleteBlogById(int id) {
		Optional<Blog> blogOptional = blogRepository.findById(id);
		Blog blog = blogOptional.get();
		List<Post> posts = postRepository.findByBlogBlogId(blog.getBlogId());
		postRepository.deleteAll(posts);
		blogRepository.deleteById(id);
	}

}