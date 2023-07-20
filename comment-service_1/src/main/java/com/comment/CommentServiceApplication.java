package com.comment;

import com.comment.exception.UrlResourceSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.UrlResource;

@SpringBootApplication
public class CommentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentServiceApplication.class, args);
	}


	@Bean
	public SimpleModule urlResourceModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(UrlResource.class, new UrlResourceSerializer());
		return module;
	}

}
