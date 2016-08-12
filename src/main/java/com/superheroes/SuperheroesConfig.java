package com.superheroes;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@EnableNeo4jRepositories
public class SuperheroesConfig extends Neo4jConfiguration {

    public static final String SUPERHEROES_DB = "superheroes.db";

    public SuperheroesConfig() {
        setBasePackage("com.superheroes");
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase(SUPERHEROES_DB);
    }
}
