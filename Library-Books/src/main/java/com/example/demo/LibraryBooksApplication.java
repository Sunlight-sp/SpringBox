package com.example.demo;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.demo.entity.Book;

@SpringBootApplication
@EnableCaching
public class LibraryBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBooksApplication.class, args);
	}
	
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//	    return new JedisConnectionFactory();
//	}
//
//	@Bean
//	public RedisTemplate<String, Book> redisTemplate() {
//	    RedisTemplate<String, Book> template = new RedisTemplate<>();
//	    template.setConnectionFactory(jedisConnectionFactory());
//	    return template;
//	}

}
