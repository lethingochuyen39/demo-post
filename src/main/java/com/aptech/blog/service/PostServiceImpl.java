package com.aptech.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.blog.model.Post;
import com.aptech.blog.repository.BlogRepository;
import com.aptech.blog.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService<Post> {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private BlogRepository blogRepository;

}
