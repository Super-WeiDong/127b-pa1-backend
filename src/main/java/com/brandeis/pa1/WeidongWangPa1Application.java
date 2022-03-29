package com.brandeis.pa1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WeidongWangPa1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(WeidongWangPa1Application.class, args);
	}

}
