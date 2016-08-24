package com.superheroes.controller.converter;

import java.text.ParseException;

public interface Converter<R, S> {

    S fromDTO(R value) throws ParseException;

    R toDTO(S value);

}
