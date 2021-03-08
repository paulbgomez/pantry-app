package com.pantry.app.pantry.microserver.pantrymicroserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableSwagger2
public class PantryMicroserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(PantryMicroserverApplication.class, args);
	}


}
