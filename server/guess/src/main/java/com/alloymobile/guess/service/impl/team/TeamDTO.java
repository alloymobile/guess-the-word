package com.alloymobile.guess.service.impl.team;

import com.alloymobile.guess.service.dto.IGuessDTO;
import lombok.Data;

@Data
public class TeamDTO implements IGuessDTO {
    private Long id;
    private String name;
}
