package com.superheroes.controller.converter;

import com.superheroes.controller.entity.SuperheroDTO;
import com.superheroes.entity.Publisher;
import com.superheroes.entity.Superhero;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.stream.Collectors.toList;

@Component
public class SuperheroDTOConverter implements Converter<SuperheroDTO, Superhero> {

    @Override
    public Superhero fromDTO(SuperheroDTO superheroDTO) {
        Superhero superhero = new Superhero();

        superhero.setName(superheroDTO.getName());
        superhero.setPseudonym(superheroDTO.getPseudonym());
        superhero.setPublisher(Publisher.valueOf(superheroDTO.getPublisher()));
        superhero.setSkills(superheroDTO.getSkills());
        superhero.setFirstAppearance(LocalDateTime.parse(superheroDTO.getFirstAppearance()));
        superhero.setAllies(superheroDTO.getAllies().stream().map(this::fromDTO).collect(toList()));
        return superhero;
    }

    @Override
    public SuperheroDTO toDTO(Superhero superhero) {
        return new SuperheroDTO(superhero.getName(), superhero.getPseudonym());
    }
}
