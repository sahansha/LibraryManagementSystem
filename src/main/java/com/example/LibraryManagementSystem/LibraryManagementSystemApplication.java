package com.example.LibraryManagementSystem;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(
		title = "Library Management System",
		version = "1.0",
		description = "Digital Library to manage books",
		contact = @Contact(
				email = "sahansha@gmail.com",
				name = "Sahansha",
				url = "www.library.com"),
		license = @License(
				name = "Apache 2.0",
				url = "https://www.apache.org/licenses/LICENSE-2.0"
		)
	),
		externalDocs = @ExternalDocumentation(
				description = "Library Management System",
				url = "www.sahansha.com"
		)
)
@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

}
