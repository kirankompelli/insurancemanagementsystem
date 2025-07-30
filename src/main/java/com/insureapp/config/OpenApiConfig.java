package com.insureapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI insuranceManagementOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Insurance Management System API")
						.description("This API allows users to register, authenticate, manage policies, claims, accounts, and payments.")
						.version("1.0.0")
						.contact(new Contact()
								.name("kompelli kiran")
								.email("kompellikiran15241@gmail.com")
								.url("https://github.com/your-github-profile")
								)
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))
					   )
				  .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
		            .components(new Components()
		                    .addSecuritySchemes("bearer-key", new SecurityScheme()
		                        .name("Authorization")
		                        .type(SecurityScheme.Type.HTTP)
		                        .scheme("bearer")
		                        .bearerFormat("JWT")
		                    )
		                );
	}

}
