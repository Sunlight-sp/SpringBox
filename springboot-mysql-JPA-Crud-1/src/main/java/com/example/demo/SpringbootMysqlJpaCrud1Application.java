package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableCaching
public class SpringbootMysqlJpaCrud1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMysqlJpaCrud1Application.class, args);
	}
	
//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("employees");
//    }
	
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
