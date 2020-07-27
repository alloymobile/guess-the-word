package com.alloymobile.guess.service.mapper;

import com.alloymobile.guess.persistence.dbo.IGuessDBO;
import com.alloymobile.guess.service.dto.IGuessDTO;

import javax.validation.constraints.NotNull;

public class GuessMapperPair<DBO_TYPE extends IGuessDBO, DTO_TYPE extends IGuessDTO> {
    public final DBO_TYPE dbo;
    public final DTO_TYPE dto;

    public GuessMapperPair(@NotNull DBO_TYPE dbo_type, @NotNull DTO_TYPE dto_type) {
        this.dbo = dbo_type;
        this.dto = dto_type;
    }
}
