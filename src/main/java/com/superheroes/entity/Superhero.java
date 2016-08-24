package com.superheroes.entity;


import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NodeEntity
public class Superhero {

    @GraphId
    private Long id;

    @Indexed(indexName = "superhero_name_idx", indexType = IndexType.FULLTEXT)
    private String name;
    private String pseudonym;
    private Publisher publisher;
    private List<String> skills;

    @RelatedTo(type = "ally", direction = Direction.BOTH)
    @Fetch
    private Set<Superhero> allies;
    private Date firstAppearance;

    public Superhero() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Set<Superhero> getAllies() {
        return allies;
    }

    public void setAllies(Set<Superhero> allies) {
        this.allies = allies;
    }

    public Date getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(Date firstAppearance) {
        this.firstAppearance = firstAppearance;
    }
}
