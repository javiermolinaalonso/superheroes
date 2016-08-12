package com.superheroes.service;

import com.superheroes.entity.Superhero;

public interface SuperheroesService {

    Iterable<Superhero> getAll();

    Superhero getByName(String name);

    Superhero create(Superhero superhero);
}
