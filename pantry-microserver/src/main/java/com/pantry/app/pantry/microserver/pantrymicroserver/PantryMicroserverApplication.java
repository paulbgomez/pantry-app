package com.pantry.app.pantry.microserver.pantrymicroserver;

import com.pantry.app.pantry.microserver.pantrymicroserver.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
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
