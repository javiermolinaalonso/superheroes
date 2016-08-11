package com.superheroes.service;

import com.superheroes.entity.Superhero;
import com.superheroes.repository.SuperheroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperheroesServiceImpl implements SuperheroesService {

    private final SuperheroesRepository superheroesRepository;

    @Autowired
    public SuperheroesServiceImpl(SuperheroesRepository superheroesRepository) {
        this.superheroesRepository = superheroesRepository;
    }

    @Override
    public List<Superhero> getAll() {
        return null;
    }

    @Override
    public Superhero getByName(String name) {
        return null;
    }

    @Override
    public Superhero create(Superhero superhero) {
        return superheroesRepository.save(superhero);
    }
}
