package com.spring.cloud.microservice.app.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.curso.microservicios.commons.alumnos.models.entity",
			 "com.spring.cloud.microservice.app.courses.models.entity",
			 "com.curso.microservicios.commons.examenes.models.entity"})
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
