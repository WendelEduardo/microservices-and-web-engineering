package br.com.fiap.mspagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicrosservicosDePagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrosservicosDePagamentosApplication.class, args);
	}

}
