package com.alloymobile.guess.service.impl.category;

import com.alloymobile.guess.service.dto.IGuessDTO;
import lombok.Data;

@Data
public class CategoryDTO implements IGuessDTO {
    private Long id;
    private String name;
}
