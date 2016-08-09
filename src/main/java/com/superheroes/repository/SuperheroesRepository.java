package com.superheroes.repository;

import com.superheroes.entity.Superhero;

import java.util.List;

public interface SuperheroesRepository {

    void save(Superhero superhero);

    Superhero getByName(Superhero superhero);

    List<Superhero> getAll();
}
