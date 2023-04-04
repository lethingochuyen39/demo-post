package com.aptech.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.aptech.blog.model.Blog;
import com.aptech.blog.repository.BlogRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository repository;

	public Page<Blog> getAllPage(String title, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (title == null) {
			return repository.findAll(pageable);
		} else {
			return repository.findByTitleContainingIgnoreCase(title, pageable);
		}
	}

	public void deleteById(int blog) {
		repository.deleteById(blog);
	}

	public void add(Blog blog) {
		repository.save(blog);
	}

	public void update(int blogId, Blog blog) {
		Optional<Blog> blogData = repository.findById(blogId);
		if (blogData.isPresent()) {
			Blog _blog = blogData.get();
			_blog.setTitle(blog.getTitle()).setUrl(blog.getUrl()).setRating(blog.getRating());
			repository.save(_blog);
		}
	}

	public Blog getById(int blogId) {
		Optional<Blog> blog = repository.findById(blogId);
		if (blog.isPresent()) {
			return blog.get();
		}
		return null;
	}

	// public List<Blog> find(String title) {

	// 	List<Blog> bList = new ArrayList<>();
	// 	if (title == null) {
	// 		repository.findAll().forEach(bList::add);
	// 	} else {
	// 		repository.findByTitleContaining(title).forEach(bList::add);
	// 	}
	// 	return bList;
	// }

}
