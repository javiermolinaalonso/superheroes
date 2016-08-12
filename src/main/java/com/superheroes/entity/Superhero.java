package com.superheroes.entity;


import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import java.time.LocalDateTime;
import java.util.List;

@NodeEntity
public class Superhero {

    @GraphId
    private Long id;

    @Indexed(indexName = "superhero_name_idx", indexType = IndexType.FULLTEXT)
    private String name;
    private String pseudonym;
    private Publisher publisher;
    private List<Skill> skills;

    @RelatedTo(type = "ally", direction = Direction.BOTH)
    @Fetch
    private List<Superhero> allies;
    private LocalDateTime firstAppearance;

    public Superhero() {
    }

    public Long getId() {
        return id;
    }

    public Superhero setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Superhero setName(String name) {
        this.name = name;
        return this;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public Superhero setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
        return this;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Superhero setPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Superhero setSkills(List<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public List<Superhero> getAllies() {
        return allies;
    }

    public Superhero setAllies(List<Superhero> allies) {
        this.allies = allies;
        return this;
    }

    public LocalDateTime getFirstAppearance() {
        return firstAppearance;
    }

    public Superhero setFirstAppearance(LocalDateTime firstAppearance) {
        this.firstAppearance = firstAppearance;
        return this;
    }
}
