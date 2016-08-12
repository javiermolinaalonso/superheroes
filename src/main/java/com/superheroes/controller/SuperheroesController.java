package com.superheroes.controller;

import com.superheroes.controller.converter.SuperheroDTOConverter;
import com.superheroes.controller.entity.SuperheroDTO;
import com.superheroes.entity.Superhero;
import com.superheroes.service.SuperheroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "/superheroes")
public class SuperheroesController {

    private final SuperheroesService superheroesService;
    private final SuperheroDTOConverter superheroConverter;

    @Autowired
    public SuperheroesController(SuperheroesService superheroesService, SuperheroDTOConverter superheroConverter) {
        this.superheroesService = superheroesService;
        this.superheroConverter = superheroConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<SuperheroDTO> getSuperHeroes() {
        Iterable<Superhero> superheroes = superheroesService.getAll();
        List<SuperheroDTO> superheroDTOs = new ArrayList<>();
        for (Superhero superheroe : superheroes) {
            superheroDTOs.add(superheroConverter.toDTO(superheroe));
        }
        return superheroDTOs;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createSuperhero(@RequestBody SuperheroDTO superheroDTO) {
        Superhero superhero = superheroConverter.fromDTO(superheroDTO);
        superheroesService.create(superhero);
    }
}
