package com.alloymobile.guess.service.dto;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;

public class GuessDTOPagedResources<DTO_TYPE extends IGuessDTO> extends PagedModel<DTO_TYPE> implements IGuessDTO {

    public static PageMetadata getPageMetadata(Page<?> page) {
        return new PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
    }

    public GuessDTOPagedResources(Collection<DTO_TYPE> content, Page<?> page, Link... links) {
        super(content, GuessDTOPagedResources.getPageMetadata(page),links);
    }

    public GuessDTOPagedResources(Collection<DTO_TYPE> content, Page<?> page, Iterable<Link> links) {
        super(content, GuessDTOPagedResources.getPageMetadata(page), links);
    }


}
