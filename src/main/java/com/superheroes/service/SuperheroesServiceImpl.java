package com.superheroes.service;

import com.superheroes.entity.Superhero;
import com.superheroes.repository.SuperheroesRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperheroesServiceImpl implements SuperheroesService {

    private final SuperheroesRepository superheroesRepository;
    private final GraphDatabaseService graphdb;

    @Autowired
    public SuperheroesServiceImpl(SuperheroesRepository superheroesRepository, GraphDatabaseService graphdb) {
        this.superheroesRepository = superheroesRepository;
        this.graphdb = graphdb;
    }

    @Override
    public Iterable<Superhero> getAll() {
        Transaction transaction = graphdb.beginTx();
        try {
            return superheroesRepository.findAll();
        } finally {
            transaction.success();
        }
    }

    @Override
    public Optional<Superhero> getByName(String name) {
        return Optional.ofNullable(superheroesRepository.findByName(name));
    }

    @Override
    public Superhero create(Superhero superhero) {
        return superheroesRepository.save(superhero);
    }
}
