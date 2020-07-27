package com.alloymobile.guess.web;

import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.service.dto.GuessDTOPagedResources;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.impl.game.GameDTO;
import com.alloymobile.guess.service.impl.game.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/game")
public class GameResource {

    private GameService gameService;

    public GameResource(GameService gameService){
        this.gameService = gameService;
    }

    //get one game by Id
    @Operation(summary = "Find game by ID", description = "Returns a single game", tags = { "Game" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = GameDTO.class))),
            @ApiResponse(responseCode = "404", description = "Game not found") })
    @GetMapping(value = "/{gameId}", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOResource<GameDTO> readGameById(@Nullable @PathVariable(name="gameId") Long gameId) {
        return gameService.readGameById(gameId).orElseThrow(NotFoundException::new);
    }

    //Get all game
    @Operation(summary = "Find all games", description = "Get all games", tags = { "Game" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = GameDTO.class)))) })
    @GetMapping( produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOPagedResources<GuessDTOResource<GameDTO>> readAllGame( @Nullable Pageable pageable ) {
        return gameService.readAllGame(pageable).orElseThrow(NotFoundException::new);
    }

    //add one game
    @Operation(summary = "Add a new game", description = "It will add a new game", tags = { "Game" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game created",
                    content = @Content(schema = @Schema(implementation = GameDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Game already exists") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody GuessDTOResource<GameDTO> createGame(@Nullable @RequestBody GameDTO newObject ) {
        return gameService.createGame(newObject).orElseThrow(NotFoundException::new);
    }

    //update a game by id
//    @Operation(summary = "Update an existing game", description = "It will update the game object", tags = { "Game" })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation"),
//            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
//            @ApiResponse(responseCode = "404", description = "Game not found"),
//            @ApiResponse(responseCode = "405", description = "Validation exception") })
//    @PutMapping(value = "/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public @ResponseBody GuessDTOResource<TeamDTO> updateGameById(@Nullable @PathVariable("gameId") Long id,@Nullable @RequestBody TeamDTO updatedObject) {
//        return gameService.updateGameById(id, updatedObject).orElseThrow(NotFoundException::new);
//    }

    //delete a game by id
    @Operation(summary = "Deletes a game", description = "It will delete game when provided id", tags = { "Game" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Game not found") })
    @DeleteMapping(value = "/{gameId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteGameById(@Nullable @PathVariable("gameId") Long id) {
        gameService.deleteGameById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
