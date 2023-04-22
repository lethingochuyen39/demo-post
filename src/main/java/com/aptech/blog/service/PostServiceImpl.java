package com.aptech.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aptech.blog.model.Blog;
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
