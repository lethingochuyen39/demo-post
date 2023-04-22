package com.aptech.blog;

import java.util.Random;
import com.aptech.blog.model.Blog;
import com.aptech.blog.repository.BlogRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BlogApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BlogApplication.class, args);
		// BlogRepository repository = context.getBean(BlogRepository.class);
		// Random random = new Random();

		// for (int i = 1; i <= 20; i++) {
		// Blog blog = new Blog();
		// blog.setUrl("http://localhost:8080/blogs/" + i)
		// .setRating(random.ints(1, 5).findFirst().getAsInt());
		// repository.save(blog);
		// }
	}

}