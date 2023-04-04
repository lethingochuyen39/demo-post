package com.aptech.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
// import java.util.List;
import com.aptech.blog.model.Blog;

//bean,@Repository(service/repository => @Component)
public interface BlogRepository extends PagingAndSortingRepository<Blog,Integer>, CrudRepository<Blog,Integer> {
	
	// List<Blog> findByTitleContaining(String title);
	Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}

	