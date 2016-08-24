package com.superheroes.controller.converter;

import com.superheroes.controller.entity.SuperheroDTO;
import com.superheroes.controller.exception.InvalidDateException;
import com.superheroes.controller.exception.InvalidPublisherException;
import com.superheroes.entity.Publisher;
import com.superheroes.entity.Superhero;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Component
public class SuperheroDTOConverter implements Converter<SuperheroDTO, Superhero> {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_PATTERN);

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

        if (superheroDTO.getFirstAppearance() != null) {
            try {
                superhero.setFirstAppearance(DATE_FORMATTER.parse(superheroDTO.getFirstAppearance()));
            } catch (ParseException e) {
                throw new InvalidDateException();
            }
        }

        if (!CollectionUtils.isEmpty(superheroDTO.getAllies())) {
            superhero.setAllies(superheroDTO.getAllies().stream().map(this::fromDTO).collect(toSet()));
        }
        return superhero;
    }

    @Override
    public SuperheroDTO toDTO(Superhero superhero) {
        return toDTO(superhero, true);
    }

    private SuperheroDTO toDTO(Superhero superhero, boolean allies) {
        SuperheroDTO superheroDTO = new SuperheroDTO(superhero.getName(), superhero.getPseudonym(), superhero.getPublisher().name());
        if (superhero.getFirstAppearance() != null) {
            superheroDTO.setFirstAppearance(DATE_FORMATTER.format(superhero.getFirstAppearance()));
        }
        superheroDTO.setSkills(superhero.getSkills());

        if(allies && !CollectionUtils.isEmpty(superhero.getAllies())) {
            superheroDTO.setAllies(superhero.getAllies().stream().map(ally -> toDTO(ally, false)).collect(toList()));
        }

        return superheroDTO;
    }
}
