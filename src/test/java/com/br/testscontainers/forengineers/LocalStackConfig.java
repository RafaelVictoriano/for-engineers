package com.br.testscontainers.forengineers;

import org.junit.Rule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
class LocalStackConfig {

	final static DockerImageName localstackImage = DockerImageName.parse("localstack/localstack:latest");


	@Container
	static LocalStackContainer localstack = new LocalStackContainer(localstackImage)
			.withReuse(true)
			.withServices(LocalStackContainer.Service.DYNAMODB, LocalStackContainer.Service.SQS)
			.withFileSystemBind("./docker/localstack", "/docker-entrypoint-initaws.d", BindMode.READ_WRITE)
			.waitingFor(Wait.forLogMessage(".*Ready.*\\n", 1));


	@DynamicPropertySource
	static void neo4jProperties(DynamicPropertyRegistry registry) {
		registry.add("aws.local", () -> true);
		registry.add("aws.endpointg", localstack::getEndpoint);
		registry.add("aws.region", localstack::getRegion);
	}

}
