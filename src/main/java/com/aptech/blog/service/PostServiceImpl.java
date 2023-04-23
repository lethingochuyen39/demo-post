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
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Override
	public List<Post> findAllByBlogId(int blogId) {
		return postRepository.findByBlogBlogId(blogId);
	}

	@Override
	public Post findById(int postId) throws Exception {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			return post.get();
		} else {
			throw new Exception("Not found");
		}
	}

	@Override
	public Post addPost(Post post, int blogId) throws Exception {

		boolean exists = postRepository.existsById(post.getPostId());
		if (exists) {
			throw new Exception("Post with id " + post.getPostId() + " already exists");
		}
		// nếu post chưa tồn tại ,tìm kiếm blog tương ứng với blogId
		Post _post = blogRepository.findById(blogId).map(blog -> {
			// nếu có blog set blog cho post
			post.setBlog(blog);
			// lưu post mới vào db
			return postRepository.save(post);
		}).orElseThrow(() -> new Exception("Not found blog with id = " + blogId));
		return _post;

	}

	@Override
	public Post updatePost(Post post, int postId) throws Exception {
		Optional<Post> dataPost = postRepository.findById(postId);

		if (dataPost.isPresent()) {
			Post _post = dataPost.get();
			_post.setTitle(post.getTitle()).setContent(post.getContent());
			return postRepository.save(_post);
		} else {
			throw new Exception("PostId not found");
		}
	}

	@Override
	public boolean deleteById(int postId) {
		postRepository.deleteById(postId);
		return true;
	}

	@Override
	public void deleteAllPostsByBlogId(int blogId) throws Exception {
		if (!blogRepository.existsById(blogId)) {
			throw new Exception("Not found Department with id = " + blogId);
		}
		postRepository.deleteAllPostsByBlogBlogId(blogId);
	}

	@Override
	public List<Post> findByTitle(String title) {
		return postRepository.findByTitleContaining(title);
	}

}
