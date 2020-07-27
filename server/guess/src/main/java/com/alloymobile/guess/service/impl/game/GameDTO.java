package com.alloymobile.guess.service.impl.game;

import com.alloymobile.guess.service.dto.IGuessDTO;
import lombok.Data;

@Data
public class GameDTO implements IGuessDTO {
    private Long id;
    private Long score;
    private Long roundId;
    private Long teamId;
    private Long wordId;
}
