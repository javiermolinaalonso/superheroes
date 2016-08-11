package com.superheroes.controller;

import com.superheroes.controller.converter.SuperheroDTOConverter;
import com.superheroes.controller.entity.SuperheroDTO;
import com.superheroes.entity.Superhero;
import com.superheroes.service.SuperheroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/superheroes")
public class SuperheroesController {

    private final SuperheroesService superheroesService;
    private final SuperheroDTOConverter superheroConverter;

    @Autowired
    public SuperheroesController(SuperheroesService superheroesService, SuperheroDTOConverter superheroConverter) {
        this.superheroesService = superheroesService;
        this.superheroConverter = superheroConverter;
    }

    @RequestMapping
    public SuperheroDTO getSuperHeroes() {
        return new SuperheroDTO("Batman", "The dark knight");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createSuperhero(SuperheroDTO superheroDTO) {
        Superhero superhero = superheroConverter.fromDTO(superheroDTO);
        superheroesService.create(superhero);
    }
}
