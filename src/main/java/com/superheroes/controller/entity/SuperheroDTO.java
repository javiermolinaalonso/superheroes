package com.superheroes.controller.entity;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SuperheroDTO {

    @NotNull(message = "Cannot be null")
    private String name;

    @NotNull(message = "Cannot be null")
    private String pseudonym;

    @NotNull(message = "Cannot be null")
    private String publisher;

    private List<String> skills;
    private List<SuperheroDTO> allies;
    private String firstAppearance;

    public SuperheroDTO() {
    }

    public SuperheroDTO(String name, String pseudonym, String publisher) {
        this.name = name;
        this.pseudonym = pseudonym;
        this.publisher = publisher;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuperheroDTO that = (SuperheroDTO) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pseudonym != null ? !pseudonym.equals(that.pseudonym) : that.pseudonym != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;
        if (skills != null ? !skills.equals(that.skills) : that.skills != null) return false;
        if (allies != null ? !allies.equals(that.allies) : that.allies != null) return false;
        return !(firstAppearance != null ? !firstAppearance.equals(that.firstAppearance) : that.firstAppearance != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pseudonym != null ? pseudonym.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        result = 31 * result + (allies != null ? allies.hashCode() : 0);
        result = 31 * result + (firstAppearance != null ? firstAppearance.hashCode() : 0);
        return result;
    }
}
