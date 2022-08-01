package com.crud.saladereuniao.saladereuniao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SaladereuniaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaladereuniaoApplication.class, args);
	}

	//configura o Bean do restTemplate
	//@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		//return builder.build();
	//}

}
