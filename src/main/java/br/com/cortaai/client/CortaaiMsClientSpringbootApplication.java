package br.com.cortaai.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CortaaiMsClientSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CortaaiMsClientSpringbootApplication.class, args);
	}

}
