package com.example.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:persistence.xml"})
public class AutomationApplication {  

	public static void main(String[] args) {
		SpringApplication.run(AutomationApplication.class, args);
		
	}

}
