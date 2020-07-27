package com.alloymobile.guess.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.HashMap;
import java.util.Map;

public class GuessDTOResource<DTO_TYPE extends IGuessDTO> extends EntityModel<DTO_TYPE> implements IGuessDTO {
    public GuessDTOResource(DTO_TYPE content, Link... links) {
        super(content, links);
    }

    public GuessDTOResource(DTO_TYPE content, Iterable<Link> links) {
        super(content, links);
    }

    @JsonProperty("_embedded")
    private final Map<String, IGuessDTO> embedded = new HashMap<>();

    public void embedResource(String relationship, IGuessDTO dto) {
        embedded.put(relationship, dto);
    }
}
