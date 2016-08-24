package com.superheroes.controller.converter;

import com.superheroes.controller.entity.SuperheroDTO;
import com.superheroes.controller.exception.InvalidPublisherException;
import com.superheroes.entity.Publisher;
import com.superheroes.entity.Superhero;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.stream.Collectors.toSet;

@Component
public class SuperheroDTOConverter implements Converter<SuperheroDTO, Superhero> {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public Superhero fromDTO(SuperheroDTO superheroDTO) {
        Superhero superhero = new Superhero();

        superhero.setName(superheroDTO.getName());
        superhero.setPseudonym(superheroDTO.getPseudonym());
        try {
            superhero.setPublisher(Publisher.valueOf(superheroDTO.getPublisher()));
        } catch (IllegalArgumentException e) {
            throw new InvalidPublisherException();
        }
        superhero.setSkills(superheroDTO.getSkills());

        if(superheroDTO.getFirstAppearance() != null) {
            superhero.setFirstAppearance(LocalDate.parse(superheroDTO.getFirstAppearance(), DateTimeFormatter.ofPattern(DATE_PATTERN)));
        }

        if (!CollectionUtils.isEmpty(superheroDTO.getAllies())) {
            superhero.setAllies(superheroDTO.getAllies().stream().map(this::fromDTO).collect(toSet()));
        }
        return superhero;
    }

    @Override
    public SuperheroDTO toDTO(Superhero superhero) {
        return new SuperheroDTO(superhero.getName(), superhero.getPseudonym());
    }
}
