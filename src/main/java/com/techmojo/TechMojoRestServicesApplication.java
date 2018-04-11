package com.techmojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling	
@ComponentScans({ @ComponentScan("com.techmojo.service")
})
@EntityScan("com.techmojo.model")
@EnableJpaRepositories("com.techmojo.service")

public class TechMojoRestServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechMojoRestServicesApplication.class, args);
	}
}
