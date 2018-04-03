package com.shulipeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@ServletComponentScan
@MapperScan("com.shulipeng.dao")
@SpringBootApplication
public class AutocodeApplication {

	private static String[] args;
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		AutocodeApplication.args = args;
		AutocodeApplication.context = SpringApplication.run(AutocodeApplication.class, args);
	}

	public static void restart() {
		context.close();
		AutocodeApplication.context = SpringApplication.run(AutocodeApplication.class, args);

	}
}
