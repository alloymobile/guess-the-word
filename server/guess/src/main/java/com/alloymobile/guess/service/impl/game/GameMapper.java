package com.alloymobile.guess.service.impl.game;

import com.alloymobile.guess.exception.InternalServerException;
import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.persistence.dbo.Game;
import com.alloymobile.guess.service.impl.round.RoundService;
import com.alloymobile.guess.service.impl.team.TeamService;
import com.alloymobile.guess.service.impl.word.WordService;
import com.alloymobile.guess.service.mapper.GuessMapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class GameMapper extends GuessMapper<Game, GameDTO> {

    private TeamService teamService;
    private RoundService roundService;
    private WordService wordService;

    public GameMapper(TeamService teamService, RoundService roundService, WordService wordService) {
        this.teamService = teamService;
        this.roundService = roundService;
        this.wordService = wordService;
    }

    @Override
    protected void populateDBO(@NotNull Game dbo, @NotNull GameDTO dto) {
        if(dto.getId() != null) {
            dbo.setId(dto.getId());
        }
        dbo.setScore(dto.getScore());
        if(dto.getTeamId() != null && dto.getRoundId() != null && dto.getWordId() != null){
            dbo.setTeamGame(teamService.findById(dto.getTeamId()).orElseThrow(NotFoundException::new));
            dbo.setRoundGame(roundService.findById(dto.getRoundId()).orElseThrow(NotFoundException::new));
            dbo.setWordGame(wordService.findById(dto.getWordId()).orElseThrow(NotFoundException::new));
        }else{
            throw new InternalServerException("Must provide all id");
        }
    }

    @Override
    protected GameDTO toDTOImpl(@NotNull Game dbo) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(dbo.getId());
        gameDTO.setScore(dbo.getScore());
        gameDTO.setTeamId(dbo.getTeamGame().getId());
        gameDTO.setRoundId(dbo.getRoundGame().getId());
        gameDTO.setWordId(dbo.getWordGame().getId());
        return gameDTO;
    }

}
