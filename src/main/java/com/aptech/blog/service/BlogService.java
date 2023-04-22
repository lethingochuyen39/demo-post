package com.aptech.blog.service;

import java.util.List;
import java.util.Optional;

import com.aptech.blog.model.Blog;

public interface BlogService<T> {

	public List<Blog> findAllBlogs();

	public Optional<Blog> findBlogById(int id);

	public Blog addBlog(Blog blog);

	public Blog updateBlog(Blog blog, int id);

	public void deleteBlogById(int id);

}
