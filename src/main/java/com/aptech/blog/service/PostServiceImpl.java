package com.aptech.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Post> findBlogsByBlogId(int blogId) {
		return postRepository.findByBlogBlogId(blogId);
	}

	@Override
	public Optional<Post> findPostById(int postId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		return postOptional;
	}

	@Override
	public Post addPost(Post post, int blogId) throws Exception {

		boolean exits = postRepository.existsById(post.getPostId());
		if (exits) {
			throw new Exception("exits employee");
		}
		Post _post = blogRepository.findById(blogId).map(blog -> {
			post.setBlog(blog);
			return postRepository.save(post);
		}).orElseThrow(() -> new Exception("Not found blog with id = " + blogId));

		return post;
	}

	@Override
	public Post updatePost(Post post, int postId) throws Exception {
		Optional<Post> dataPost = postRepository.findById(postId);

		if (dataPost.isPresent()) {
			Post _post = dataPost.get();
			_post.setTitle(post.getTitle()).setContent(post.getContent())
					.setBlog(post.getBlog());
			return postRepository.save(_post);

		} else {
			throw new Exception("PostId not found");
		}
	}

	@Override
	public void deletePostByPostId(int postId) {
		postRepository.deleteById(postId);
	}

	@Transactional
	@Override
	public void deleteByBlogId(int blogId) throws Exception {
		if (!blogRepository.existsById(blogId)) {
			throw new Exception("Not found Department with id = " + blogId);
		}
		postRepository.deleteByBlogBlogId(blogId);
	}

	@Override
	public List<Post> findByTitle(String title) {
		return postRepository.findByTitleContaining(title);
	}

}
