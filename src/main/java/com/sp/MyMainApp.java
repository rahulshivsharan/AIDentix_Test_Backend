package com.sp;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyMainApp {

	private static final Logger logger = LoggerFactory.getLogger(MyMainApp.class);
	
	public static void main(String[] args) {
		try {			
			SpringApplication application = new SpringApplication(MyMainApp.class);
			application.run(args);			
			logger.info("Spring Boot application started");
		}catch(Exception e) {
			logger.error("Spring Boot application exception "+e.getMessage());			
		}
		
	}

}
