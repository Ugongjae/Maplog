package com.maplog.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:datasource.properties")
public class BApplication {

	public static void main(String[] args) {
		SpringApplication.run(BApplication.class, args);
	}

}
