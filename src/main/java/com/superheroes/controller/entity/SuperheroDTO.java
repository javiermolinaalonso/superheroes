package com.superheroes.controller.entity;

public class SuperheroDTO {

    private String name;
    private String pseudonim;

    public SuperheroDTO() {
    }

    public SuperheroDTO(String name, String pseudonim) {
        this.name = name;
        this.pseudonim = pseudonim;
    }

    public String getName() {
        return name;
    }

    public SuperheroDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPseudonym() {
        return pseudonim;
    }

    public SuperheroDTO setPseudonim(String pseudonim) {
        this.pseudonim = pseudonim;
        return this;
    }
}
