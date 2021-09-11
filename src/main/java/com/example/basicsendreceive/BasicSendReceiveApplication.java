package com.example.basicsendreceive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BasicSendReceiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSendReceiveApplication.class, args);
	}

	@Bean
	@ConditionalOnProperty(name = "role", havingValue = "help", matchIfMissing = true)
	public CommandLineRunner help() {
		return (args) -> {
			System.out.println("Syntax:");
			System.out.println("./run --role=publisher [--exchange=reactive-text] [--count=10] [--delay=10s]");
			System.out.println("./run --role=consumer [--queue=reactive-text] [--exchange=reactive-text]");
		} ;
	}

}
