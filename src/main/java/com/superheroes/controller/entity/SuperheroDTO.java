package com.superheroes.controller.entity;

public class SuperheroDTO {

    private String name;
    private String pseudonym;

    public SuperheroDTO() {
    }

    public SuperheroDTO(String name, String pseudonym) {
        this.name = name;
        this.pseudonym = pseudonym;
    }

    public String getName() {
        return name;
    }

    public SuperheroDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public SuperheroDTO setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
        return this;
    }
}
