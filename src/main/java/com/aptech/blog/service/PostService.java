package com.aptech.blog.service;

import java.util.List;
import java.util.Optional;

import com.aptech.blog.model.Post;

public interface PostService {

	public List<Post> findAllByBlogId(int blogId);

	public Post findById(int postId) throws Exception;

	public Post addPost(Post post, int blogId) throws Exception;

	public Post updatePost(Post post, int postId) throws Exception;

	public void deleteAllPostsByBlogId(int blogId) throws Exception;

	public boolean deleteById(int postId);

	public List<Post> findByTitle(String title);
}
