package com.alloymobile.guess.service.impl.category;

import com.alloymobile.guess.persistence.dbo.Category;
import com.alloymobile.guess.service.mapper.GuessMapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class CategoryMapper extends GuessMapper<Category,CategoryDTO> {

    @Override
    protected void populateDBO(@NotNull Category dbo, @NotNull CategoryDTO dto) {
        if(dto.getId() != null) {
            dbo.setId(dto.getId());
        }
        dbo.setName(dto.getName());
    }

    @Override
    protected CategoryDTO toDTOImpl(@NotNull Category dbo) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(dbo.getId());
        categoryDTO.setName(dbo.getName());
        return categoryDTO;
    }

}
