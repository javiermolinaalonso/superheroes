package com.superheroes.controller.entity;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SuperheroDTO {

    @NotNull
    private String name;

    @NotNull
    private String pseudonym;

    @NotNull(message = "Publisher cannot be null")
    private String publisher;
    private List<String> skills;
    private List<SuperheroDTO> allies;
    private String firstAppearance;

    public SuperheroDTO() {
    }

    public SuperheroDTO(String name, String pseudonym) {
        this.name = name;
        this.pseudonym = pseudonym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<SuperheroDTO> getAllies() {
        return allies;
    }

    public void setAllies(List<SuperheroDTO> allies) {
        this.allies = allies;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }
}
