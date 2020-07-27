package com.alloymobile.guess.service.impl.round;

import com.alloymobile.guess.persistence.dbo.Round;
import com.alloymobile.guess.service.mapper.GuessMapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class RoundMapper extends GuessMapper<Round, RoundDTO> {

    @Override
    protected void populateDBO(@NotNull Round dbo, @NotNull RoundDTO dto) {
        if(dto.getId() != null) {
            dbo.setId(dto.getId());
        }
        dbo.setName(dto.getName());
    }

    @Override
    protected RoundDTO toDTOImpl(@NotNull Round dbo) {
        RoundDTO roundDTO = new RoundDTO();
        roundDTO.setId(dbo.getId());
        roundDTO.setName(dbo.getName());
        return roundDTO;
    }

}
