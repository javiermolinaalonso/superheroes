package com.superheroes.controller;

import com.superheroes.controller.entity.SuperheroDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/superheroes")
public class SuperheroesController {

    @RequestMapping
    public SuperheroDTO getSuperHeroes() {
        return new SuperheroDTO("Batman", "The dark knight");
    }
}
