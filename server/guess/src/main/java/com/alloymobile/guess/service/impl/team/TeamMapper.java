package com.alloymobile.guess.service.impl.team;

import com.alloymobile.guess.persistence.dbo.Team;
import com.alloymobile.guess.service.mapper.GuessMapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class TeamMapper extends GuessMapper<Team, TeamDTO> {

    @Override
    protected void populateDBO(@NotNull Team dbo, @NotNull TeamDTO dto) {
        if(dto.getId() != null) {
            dbo.setId(dto.getId());
        }
        dbo.setName(dto.getName());
    }

    @Override
    protected TeamDTO toDTOImpl(@NotNull Team dbo) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(dbo.getId());
        teamDTO.setName(dbo.getName());
        return teamDTO;
    }

}
