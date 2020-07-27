package com.alloymobile.guess.service.impl.word;

import com.alloymobile.guess.service.dto.IGuessDTO;
import lombok.Data;

@Data
public class WordDTO implements IGuessDTO {
    private Long id;
    private String name;
    private Long categoryId;
}
