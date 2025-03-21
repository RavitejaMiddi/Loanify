package com.loanify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@CrossOrigin(origins = "http://localhost:4200")
public class LoanifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanifyApplication.class, args);
	}

}

