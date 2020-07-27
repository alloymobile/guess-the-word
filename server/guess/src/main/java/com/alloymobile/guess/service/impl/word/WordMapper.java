package com.alloymobile.guess.service.impl.word;

import com.alloymobile.guess.exception.InternalServerException;
import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.persistence.dbo.Word;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.impl.category.CategoryService;
import com.alloymobile.guess.service.mapper.GuessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class WordMapper extends GuessMapper<Word, WordDTO> {

    @Autowired
    CategoryService categoryService;

    @Override
    protected void populateDBO(@NotNull Word dbo, @NotNull WordDTO dto) {
        if(dto.getId() != null) {
            dbo.setId(dto.getId());
        }
        dbo.setName(dto.getName());
        if(dto.getCategoryId() != null){
            dbo.setCategoryWord(categoryService.findById(dto.getCategoryId()).orElseThrow(NotFoundException::new));
        }else{
            throw new InternalServerException("Must provide Team id");
        }
    }

    @Override
    protected WordDTO toDTOImpl(@NotNull Word dbo) {
        WordDTO wordDTO = new WordDTO();
        wordDTO.setName(dbo.getName());
        return wordDTO;
    }

    @Override
    protected void embeddedResources(Word dbo, @NotNull GuessDTOResource<WordDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("category", this.categoryService.getMapper().toDTO(dbo.getCategoryWord()));
    }

}
