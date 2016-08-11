package com.superheroes.controller.converter;

public interface Converter<R, S> {

    S fromDTO(R value);

    R toDTO(S value);

}
