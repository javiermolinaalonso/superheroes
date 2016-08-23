package com.superheroes.controller;

import com.superheroes.controller.converter.SuperheroDTOConverter;
import com.superheroes.controller.entity.SuperheroDTO;
import com.superheroes.entity.Superhero;
import com.superheroes.service.SuperheroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

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
    public ResponseEntity<String> createSuperhero(@RequestBody @Valid SuperheroDTO superheroDTO, BindingResult validationResult) {
        ResponseEntity<String> response;
        if(validationResult.hasErrors()) {
            String errors = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()).collect(joining(","));
            response = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            Superhero superhero = superheroConverter.fromDTO(superheroDTO);
            superheroesService.create(superhero);
            response = new ResponseEntity<>(HttpStatus.CREATED);
        }
        return response;
    }
}
