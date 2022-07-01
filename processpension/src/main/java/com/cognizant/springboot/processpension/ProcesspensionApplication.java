package com.cognizant.springboot.processpension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.cognizant.springboot.processpension")
public class ProcesspensionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcesspensionApplication.class, args);
		System.out.println("Processpension Application has Started..!!");
	}

}
