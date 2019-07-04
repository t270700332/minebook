package com.book.tzl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.book.tzl.*.mapper")
public class MinebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinebookApplication.class, args);
	}

}
