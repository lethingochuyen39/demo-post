package com.aptech.blog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.aptech.blog.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

	// tìm ds post theo BlogId
	List<Post> findByBlogBlogId(int blogId);

	// xóa các post theo BlogId
	// void deleteByBlogBlogId(int blogId);

	// tìm ds post theo title
	List<Post> findByTitleContaining(String title);

	void deleteAllPostsByBlogBlogId(int blogId);
}
