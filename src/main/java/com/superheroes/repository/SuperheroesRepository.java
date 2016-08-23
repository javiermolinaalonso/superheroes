package com.superheroes.repository;

import com.superheroes.entity.Superhero;
import org.springframework.data.repository.CrudRepository;

public interface SuperheroesRepository extends CrudRepository<Superhero, Long>{

    Superhero findByName(String name);

}
