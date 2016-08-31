package com.superheroes.controller;

import com.superheroes.controller.converter.SuperheroDTOConverter;
import com.superheroes.controller.entity.SuperheroDTO;
import com.superheroes.controller.exception.InvalidDateException;
import com.superheroes.controller.exception.InvalidPublisherException;
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
import java.util.Optional;

import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping(value = "/superheroes")
public class SuperheroesController {

    private final SuperheroesService superheroesService;
    private final SuperheroDTOConverter superheroConverter;

    @Autowired
    public SuperheroesController(SuperheroesService superheroesService, SuperheroDTOConverter superheroConverter) {
        this.superheroesService = superheroesService;
        this.superheroConverter = superheroConverter;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<SuperheroDTO> getSuperHero(@PathVariable String name) {
        Optional<Superhero> superhero = superheroesService.getByName(name);
        ResponseEntity<SuperheroDTO> entity;
        if(superhero.isPresent()) {
            entity = new ResponseEntity<>(superheroConverter.toDTO(superhero.get()), HttpStatus.OK);
        } else {
            entity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return entity;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<SuperheroDTO> getSuperHeroes() {
        Iterable<Superhero> superheroes = superheroesService.getAll();
        List<SuperheroDTO> superheroDTOs = new ArrayList<>();
        for (Superhero superhero : superheroes) {
            superheroDTOs.add(superheroConverter.toDTO(superhero));
        }
        return superheroDTOs;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> createSuperhero(@RequestBody @Valid SuperheroDTO superheroDTO, BindingResult validationResult) {
        ResponseEntity<String> response;
        if (validationResult.hasErrors()) {
            String errors = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()).collect(joining(","));
            response = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            Superhero superhero = superheroConverter.fromDTO(superheroDTO);
            superheroesService.create(superhero);
            response = new ResponseEntity<>(HttpStatus.CREATED);
        }
        return response;
    }

    @ExceptionHandler(value = InvalidPublisherException.class)
    public ResponseEntity<String> handleIllegalArgument() {
        return new ResponseEntity<>("Invalid publisher value", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidDateException.class)
    public ResponseEntity<String> handleIllegalDate() {
        return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
    }
}
