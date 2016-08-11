package com.superheroes.service;

import com.superheroes.entity.Superhero;

import java.util.List;

public interface SuperheroesService {

    List<Superhero> getAll();

    Superhero getByName(String name);

    Superhero create(Superhero superhero);
}
