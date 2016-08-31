package com.superheroes.service;

import com.superheroes.entity.Superhero;

import java.util.Optional;

public interface SuperheroesService {

    Iterable<Superhero> getAll();

    Optional<Superhero> getByName(String name);

    Superhero create(Superhero superhero);
}
