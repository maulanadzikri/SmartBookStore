package com.smartbookstore.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartBookstoreApplication.class, args);

		System.out.println("\nClient App is running...\n");
	}

}
