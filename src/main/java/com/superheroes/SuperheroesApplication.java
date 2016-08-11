package com.superheroes;

import com.superheroes.config.SuperheroesConfig;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
public class SuperheroesApplication {

    public static void main(String[] args) throws Exception {
        FileUtils.deleteRecursively(new File(SuperheroesConfig.SUPERHEROES_DB));
        SpringApplication.run(SuperheroesApplication.class, args);
    }
}
