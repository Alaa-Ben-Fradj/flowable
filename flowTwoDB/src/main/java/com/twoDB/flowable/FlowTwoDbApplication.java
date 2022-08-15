package com.twoDB.flowable;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.twoDB.flowable.services.UserServiceImpl;

@SpringBootApplication
public class FlowTwoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowTwoDbApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final UserServiceImpl myService) {

	    return new CommandLineRunner() {
	        public void run(String... strings) throws Exception {
	            myService.createDemoUsers();
	        }
	    };
	}
}
