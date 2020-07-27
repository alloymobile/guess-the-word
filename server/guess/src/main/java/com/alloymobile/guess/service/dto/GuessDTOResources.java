package com.alloymobile.guess.service.dto;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

public class GuessDTOResources<DTO_TYPE extends IGuessDTO> extends CollectionModel<DTO_TYPE> implements IGuessDTO {
    public GuessDTOResources(Iterable<DTO_TYPE> content, Link... links) {
        super(content, links);
    }

    public GuessDTOResources(Iterable<DTO_TYPE> content, Iterable<Link> links) {
        super(content, links);
    }
}
