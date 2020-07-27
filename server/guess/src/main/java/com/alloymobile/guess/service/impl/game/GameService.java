package com.alloymobile.guess.service.impl.game;

import com.alloymobile.guess.persistence.dbo.Game;
import com.alloymobile.guess.persistence.jpa.GameRepository;
import com.alloymobile.guess.persistence.jpa.IGuessJpaRepository;
import com.alloymobile.guess.service.GuessService;
import com.alloymobile.guess.service.dto.GuessDTOPagedResources;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.mapper.GuessMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GameService extends GuessService<Game, GameDTO> {

    public GameService(GameMapper gameMapper, GameRepository gameRepository) {
        super(gameMapper, gameRepository);
    }

    public Optional<GuessDTOResource<GameDTO>> readGameById(@Nullable Long id){
        return super.readById(id);
    }

    public Optional<GuessDTOPagedResources<GuessDTOResource<GameDTO>>> readAllGame(@Nullable Pageable pageable){
        return super.readAll(pageable);
    }
    
    public Optional<GuessDTOResource<GameDTO>> createGame(@Nullable GameDTO newObject) {
        return super.createOne(this.mapper.toNewDBO(newObject));
    }

//    public Optional<GuessDTOResource<TeamDTO>> updateGameById(@Nullable Long id, @Nullable TeamDTO updatedObject) {
//        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
//    }

    public void deleteGameById(@Nullable Long id) {
        super.deleteById(id);
    }
}
