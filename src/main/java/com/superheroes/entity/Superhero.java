package com.superheroes.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Superhero {

    private final String name;
    private final String pseudonym;
    private final Publisher publisher;
    private final List<Skill> skills;
    private final List<Superhero> allies;
    private final LocalDateTime firstAppearance;

    private Superhero(Builder builder) {
        name = builder.name;
        pseudonym = builder.pseudonym;
        publisher = builder.publisher;
        skills = builder.skills;
        allies = builder.allies;
        firstAppearance = builder.firstAppearance;
    }

    public String getName() {
        return name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Superhero> getAllies() {
        return allies;
    }

    public LocalDateTime getFirstAppearance() {
        return firstAppearance;
    }

    public static final class Builder {
        private String name;
        private String pseudonym;
        private Publisher publisher;
        private List<Skill> skills;
        private List<Superhero> allies;
        private LocalDateTime firstAppearance;

        public Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withPseudonym(String val) {
            pseudonym = val;
            return this;
        }

        public Builder withPublisher(Publisher val) {
            publisher = val;
            return this;
        }

        public Builder withSkills(List<Skill> val) {
            skills = val;
            return this;
        }

        public Builder withAllies(List<Superhero> val) {
            allies = val;
            return this;
        }

        public Builder withFirstAppearance(LocalDateTime val) {
            firstAppearance = val;
            return this;
        }

        public Superhero build() {
            return new Superhero(this);
        }
    }
}
