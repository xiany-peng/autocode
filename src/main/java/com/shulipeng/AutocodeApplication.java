package com.shulipeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@MapperScan("com.shulipeng.dao.*.*")
@SpringBootApplication
public class AutocodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutocodeApplication.class, args);
	}
}
