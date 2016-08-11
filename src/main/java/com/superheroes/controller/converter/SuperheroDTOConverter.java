package com.superheroes.controller.converter;

import com.superheroes.controller.entity.SuperheroDTO;
import com.superheroes.entity.Superhero;
import org.springframework.stereotype.Component;

@Component
public class SuperheroDTOConverter implements Converter<SuperheroDTO, Superhero> {

    @Override
    public Superhero fromDTO(SuperheroDTO superheroDTO) {
        Superhero superhero = new Superhero();

        superhero.setName(superheroDTO.getName());
        superhero.setPseudonym(superheroDTO.getPseudonym());

        return superhero;
    }

    @Override
    public SuperheroDTO toDTO(Superhero superhero) {
        return new SuperheroDTO(superhero.getName(), superhero.getPseudonym());
    }
}
