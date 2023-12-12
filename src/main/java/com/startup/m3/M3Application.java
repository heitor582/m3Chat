package com.startup.m3;

import com.startup.m3.config.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class M3Application {

	public static void main(String[] args) {
		SpringApplication.run(WebServerConfig.class, args);
	}

}
