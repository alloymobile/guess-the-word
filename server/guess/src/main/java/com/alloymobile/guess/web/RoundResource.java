package com.alloymobile.guess.web;

import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.service.dto.GuessDTOPagedResources;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.impl.round.RoundDTO;
import com.alloymobile.guess.service.impl.round.RoundService;
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
@RequestMapping("/v1/api/round")
public class RoundResource {

    private RoundService roundService;

    public RoundResource(RoundService roundService){
        this.roundService = roundService;
    }

    //get one round by Id
    @Operation(summary = "Find round by ID", description = "Returns a single round", tags = { "Round" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = RoundDTO.class))),
            @ApiResponse(responseCode = "404", description = "Round not found") })
    @GetMapping(value = "/{roundId}", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOResource<RoundDTO> readRoundById(@Nullable @PathVariable(name="roundId") Long roundId) {
        return roundService.readRoundById(roundId).orElseThrow(NotFoundException::new);
    }

    //get round by name
    @Operation(summary = "Find round by name", description = "Returns a single round when a name is provided", tags = { "Round" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = RoundDTO.class))),
            @ApiResponse(responseCode = "404", description = "Round not found") })
    @GetMapping(value = "/name/{name}", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody GuessDTOResource<RoundDTO> readRoundByName(@Nullable @PathVariable(name="name") String roundName) {
        return roundService.readRoundByName(roundName).orElseThrow(NotFoundException::new);
    }

    //Get all round
    @Operation(summary = "Find all rounds", description = "Get all rounds", tags = { "Round" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RoundDTO.class)))) })
    @GetMapping( produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOPagedResources<GuessDTOResource<RoundDTO>> readAllRound( @Nullable Pageable pageable ) {
        return roundService.readAllRound(pageable).orElseThrow(NotFoundException::new);
    }

    //add one round
    @Operation(summary = "Add a new round", description = "It will add a new round", tags = { "Round" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Round created",
                    content = @Content(schema = @Schema(implementation = RoundDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Round already exists") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody GuessDTOResource<RoundDTO> createRound(@Nullable @RequestBody RoundDTO newObject ) {
        return roundService.createRound(newObject).orElseThrow(NotFoundException::new);
    }

    //update a round by id
//    @Operation(summary = "Update an existing round", description = "It will update the round object", tags = { "Round" })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation"),
//            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
//            @ApiResponse(responseCode = "404", description = "Round not found"),
//            @ApiResponse(responseCode = "405", description = "Validation exception") })
//    @PutMapping(value = "/{roundId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public @ResponseBody GuessDTOResource<TeamDTO> updateRoundById(@Nullable @PathVariable("roundId") Long id,@Nullable @RequestBody TeamDTO updatedObject) {
//        return roundService.updateRoundById(id, updatedObject).orElseThrow(NotFoundException::new);
//    }

    //delete a round by id
    @Operation(summary = "Deletes a round", description = "It will delete round when provided id", tags = { "Round" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Round not found") })
    @DeleteMapping(value = "/{roundId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteRoundById(@Nullable @PathVariable("roundId") Long id) {
        roundService.deleteRoundById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
