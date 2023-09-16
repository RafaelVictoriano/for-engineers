package com.br.testscontainers.forengineers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestForEngineersApplication {

	public static void main(String[] args) {
		SpringApplication.from(ForEngineersApplication::main).with(TestForEngineersApplication.class).run(args);
	}

}
