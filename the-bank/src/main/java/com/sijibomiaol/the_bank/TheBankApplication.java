package com.sijibomiaol.the_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TheBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheBankApplication.class, args);
	}

}
