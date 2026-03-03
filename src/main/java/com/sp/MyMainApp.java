package com.sp;


import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyMainApp {

	public static void main(String[] args) {
		try {
			SpringApplication application = new SpringApplication(MyMainApp.class);
			application.run(args);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
