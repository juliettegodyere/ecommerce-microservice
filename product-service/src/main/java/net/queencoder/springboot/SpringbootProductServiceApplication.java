package net.queencoder.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootProductServiceApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringbootProductServiceApplication.class, args);
		SpringApplication application = new SpringApplication(SpringbootProductServiceApplication.class);
		application.setAdditionalProfiles("local");
		application.run(args);
	}

}
