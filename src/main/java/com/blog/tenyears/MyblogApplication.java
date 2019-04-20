package com.blog.tenyears;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
//@EnableCaching
@EnableScheduling
@MapperScan(basePackages="com.blog.tenyears.mapper")
public class MyblogApplication {
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(MyblogApplication.class, args);
	}
	
	
}
