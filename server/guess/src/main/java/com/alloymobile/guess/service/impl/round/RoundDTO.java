package com.alloymobile.guess.service.impl.round;

import com.alloymobile.guess.service.dto.IGuessDTO;
import lombok.Data;

@Data
public class RoundDTO implements IGuessDTO {
    private Long id;
    private String name;
}
