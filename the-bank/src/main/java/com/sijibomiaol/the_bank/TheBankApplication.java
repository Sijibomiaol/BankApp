package com.sijibomiaol.the_bank;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@OpenAPIDefinition(
		info = @Info(
				 title ="The Bank App",
				description = "Backend Rest APIs for Aol Bank",
				version = "v1.0",
				contact = @Contact(
						name= "Aderinlewo Sijibomi",
						email = "aderinlewomoses@gmail.com",
						url = "https://github.com/Sijibomiaol/BankApp"

				),
				license = @License(
						name = "Aderinlewo Sijibom",
						url = "https://github.com/Sijibomiaol/BankApp"
				)


		),
		externalDocs = @ExternalDocumentation(
				description = "Documentation of the The Bank App",
				url = "https://github.com/Sijibomiaol/BankApp"
		)
)
public class TheBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheBankApplication.class, args);
	}

}
