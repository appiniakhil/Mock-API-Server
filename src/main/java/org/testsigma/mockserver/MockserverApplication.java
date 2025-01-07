package org.testsigma.mockserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class MockserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockserverApplication.class, args);
	}

}
