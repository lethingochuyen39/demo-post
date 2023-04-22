package com.aptech.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aptech.blog.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUrlContaining(String url);

}
