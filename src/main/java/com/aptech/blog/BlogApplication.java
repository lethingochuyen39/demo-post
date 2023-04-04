package com.aptech.blog;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aptech.blog.model.Blog;
import com.aptech.blog.repository.BlogRepository;

@SpringBootApplication
// ko @Repository => @EnableJpaRepositories
@EnableJpaRepositories
public class BlogApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BlogApplication.class, args);
		BlogRepository repository = context.getBean(BlogRepository.class);
		Random random = new Random();

		for (int i = 1; i <= 100; i++) {
			Blog blog = new Blog();
			blog.setBlogId(i).setTitle("Title " + i)
					.setUrl("http://localhost:8080/blog/" + i)
					// tao ra ngau nhien 1 den 5 mac dinh la string => ep kieu int dung getAsInt()
					.setRating(random.ints(1, 5).findFirst().getAsInt());
			repository.save(blog);
		}
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "DELETE", "PUT");
			}
		};
	}

}
