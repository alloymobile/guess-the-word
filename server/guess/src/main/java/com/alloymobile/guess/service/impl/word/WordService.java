package com.alloymobile.guess.service.impl.word;

import com.alloymobile.guess.persistence.dbo.Word;
import com.alloymobile.guess.persistence.jpa.WordRepository;
import com.alloymobile.guess.service.GuessService;
import com.alloymobile.guess.service.dto.GuessDTOPagedResources;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.dto.GuessDTOResources;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class WordService extends GuessService<Word, WordDTO> {

    public WordService(WordMapper wordMapper, WordRepository wordRepository) {
        super(wordMapper, wordRepository);
    }

    public Optional<GuessDTOResource<WordDTO>> readWordById(@Nullable Long id){
        return super.readById(id);
    }

    public Optional<GuessDTOPagedResources<GuessDTOResource<WordDTO>>> readAllWord(@Nullable Pageable pageable){
        return super.readAll(pageable);
    }

    public Optional<GuessDTOResource<WordDTO>> createWord(@Nullable WordDTO newObject) {
        return super.createOne(this.mapper.toNewDBO(newObject));
    }

//    public Optional<GuessDTOResource<TeamDTO>> updateWordById(@Nullable Long id, @Nullable TeamDTO updatedObject) {
//        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
//    }

    public void deleteWordById(@Nullable Long id) {
        super.deleteById(id);
    }
}
