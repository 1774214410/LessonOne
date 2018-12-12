package com.liuzw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * springboot启动类
 */
@SpringBootApplication
@EnableScheduling
//@MapperScan("com.liuzw.common.dao")
public class LessononeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LessononeApplication.class, args);
	}

}
