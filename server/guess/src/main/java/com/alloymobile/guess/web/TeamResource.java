package com.alloymobile.guess.web;

import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.service.dto.GuessDTOPagedResources;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.impl.team.TeamDTO;
import com.alloymobile.guess.service.impl.team.TeamService;
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
@RequestMapping("/v1/api/team")
public class TeamResource {

    private TeamService teamService;

    public TeamResource(TeamService teamService){
        this.teamService = teamService;
    }

    //get one team by Id
    @Operation(summary = "Find team by ID", description = "Returns a single team", tags = { "Team" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
            @ApiResponse(responseCode = "404", description = "Team not found") })
    @GetMapping(value = "/{teamId}", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOResource<TeamDTO> readTeamById(@Nullable @PathVariable(name="teamId") Long teamId) {
        return teamService.readTeamById(teamId).orElseThrow(NotFoundException::new);
    }

    //get team by description
    @Operation(summary = "Find team by name", description = "Returns a single team when a name is provided", tags = { "Team" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
            @ApiResponse(responseCode = "404", description = "Team not found") })
    @GetMapping(value = "/name/{name}", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody GuessDTOResource<TeamDTO> readTeamByName(@Nullable @PathVariable(name="name") String teamName) {
        return teamService.readTeamByName(teamName).orElseThrow(NotFoundException::new);
    }

    //Get all team
    @Operation(summary = "Find all teams", description = "Get all teams", tags = { "Team" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TeamDTO.class)))) })
    @GetMapping( produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOPagedResources<GuessDTOResource<TeamDTO>> readAllTeam( @Nullable Pageable pageable ) {
        return teamService.readAllTeam(pageable).orElseThrow(NotFoundException::new);
    }

    //add one team
    @Operation(summary = "Add a new team", description = "It will add a new team", tags = { "Team" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team created",
                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Team already exists") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody GuessDTOResource<TeamDTO> createTeam(@Nullable @RequestBody TeamDTO newObject ) {
        return teamService.createTeam(newObject).orElseThrow(NotFoundException::new);
    }

    //update a team by id
//    @Operation(summary = "Update an existing team", description = "It will update the team object", tags = { "Team" })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation"),
//            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
//            @ApiResponse(responseCode = "404", description = "Team not found"),
//            @ApiResponse(responseCode = "405", description = "Validation exception") })
//    @PutMapping(value = "/{teamId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public @ResponseBody GuessDTOResource<TeamDTO> updateTeamById(@Nullable @PathVariable("teamId") Long id,@Nullable @RequestBody TeamDTO updatedObject) {
//        return teamService.updateTeamById(id, updatedObject).orElseThrow(NotFoundException::new);
//    }

    //delete a team by id
    @Operation(summary = "Deletes a team", description = "It will delete team when provided id", tags = { "Team" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Team not found") })
    @DeleteMapping(value = "/{teamId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteTeamById(@Nullable @PathVariable("teamId") Long id) {
        teamService.deleteTeamById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
