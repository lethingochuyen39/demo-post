package com.aptech.blog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.aptech.blog.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

	List<Post> findByBlogBlogId(int blogId);

}
