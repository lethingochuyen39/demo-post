package com.aptech.blog.service;

import java.util.List;
import java.util.Optional;

import com.aptech.blog.model.Post;

public interface PostService {

	public List<Post> findBlogsByBlogId(int blogId);

	public Optional<Post> findPostById(int postId);

	public Post addPost(Post post, int blogId) throws Exception;

	public Post updatePost(Post post, int postId) throws Exception;

	public void deleteByBlogId(int blogId) throws Exception;

	public void deletePostByPostId(int postId);

	public List<Post> findByTitle(String title);
}
