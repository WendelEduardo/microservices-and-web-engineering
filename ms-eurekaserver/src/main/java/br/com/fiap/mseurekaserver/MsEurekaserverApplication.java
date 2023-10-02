package br.com.fiap.mseurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MsEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEurekaserverApplication.class, args);
	}

}
