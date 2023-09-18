package com.br.testscontainers.forengineers;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class PropostControllerTest {

    @LocalServerPort
    private Integer port;
    final String PATH_CONTRACT = "src/test/resources/files/criar-proposta.json";
    final static DockerImageName localstackImage = DockerImageName.parse("localstack/localstack:latest");

    @Container
    static LocalStackContainer localstack = new LocalStackContainer(localstackImage)
            .withReuse(true)
            .withServices(LocalStackContainer.Service.DYNAMODB, LocalStackContainer.Service.SQS)
            .withFileSystemBind("./docker/localstack", "/docker-entrypoint-initaws.d", BindMode.READ_WRITE)
            .waitingFor(Wait.forLogMessage(".*Ready.*\\n", 1));

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        System.out.println("*********************  " +localstack.getEndpoint());
        registry.add("aws.local", () -> true);
        registry.add("aws.endpoint", localstack::getEndpoint);
        registry.add("aws.region", localstack::getRegion);
    }

    @Test
    void shouldReturnStatusCreatesWhenCreateProposta() throws Exception {
        given()
                .port(8080)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(Files.readString(Path.of(PATH_CONTRACT)))
                .post("/creditos/v1/proposta")
                .then()
                .statusCode(201);
    }

    @Test
    void shouldReturnStatusCreatesWhenGetProposta() throws Exception {
        given()
                .port(8080)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(Files.readString(Path.of(PATH_CONTRACT)))
                .post("/creditos/v1/proposta")
                .then()
                .statusCode(201);
    }

}
