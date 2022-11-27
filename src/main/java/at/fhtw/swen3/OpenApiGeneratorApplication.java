package at.fhtw.swen3;

import com.fasterxml.jackson.databind.Module;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories//repo test fail
@ComponentScan(basePackages = {"at.fhtw.swen3.services", "at.fhtw.swen3.controller" , "at.fhtw.swen3.configuration", "at.fhtw.swen3.persistence"})
@Slf4j
public class OpenApiGeneratorApplication {

    public static void main(String[] args) {
        log.info("main");
        SpringApplication.run(OpenApiGeneratorApplication.class, args);
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}