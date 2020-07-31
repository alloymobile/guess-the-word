package com.alloymobile.guess.web;

import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.persistence.jpa.WordRepository;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.dto.GuessDTOResources;
import com.alloymobile.guess.service.impl.word.WordDTO;
import com.alloymobile.guess.service.impl.word.WordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/word")
public class WordResource {

    private WordService wordService;
    private WordRepository wordRepository;

    public WordResource(WordService wordService, WordRepository wordRepository){
        this.wordService = wordService;
        this.wordRepository = wordRepository;
    }



    //get one word by Id
    @Operation(summary = "Find word by ID", description = "Returns a single word", tags = { "Word" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = WordDTO.class))),
            @ApiResponse(responseCode = "404", description = "Word not found") })
    @GetMapping(value = "/{wordId}", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOResource<WordDTO> readWordById(@Nullable @PathVariable(name="wordId") Long wordId) {
        return wordService.readWordById(wordId).orElseThrow(NotFoundException::new);
    }

    //Get all word in one page
    @Operation(summary = "Find all words", description = "Get all words", tags = { "Word" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = WordDTO.class)))) })
    @GetMapping( produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOResources<GuessDTOResource<WordDTO>> readAllWord() {
        return wordService.readAllWord().orElseThrow(NotFoundException::new);
    }

    //add one word
    @Operation(summary = "Add a new word", description = "It will add a new word", tags = { "Word" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Word created",
                    content = @Content(schema = @Schema(implementation = WordDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Word already exists") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody GuessDTOResource<WordDTO> createWord(@Nullable @RequestBody WordDTO newObject ) {
        return wordService.createWord(newObject).orElseThrow(NotFoundException::new);
    }

    //update a word by id
//    @Operation(summary = "Update an existing word", description = "It will update the word object", tags = { "Word" })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation"),
//            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
//            @ApiResponse(responseCode = "404", description = "Word not found"),
//            @ApiResponse(responseCode = "405", description = "Validation exception") })
//    @PutMapping(value = "/{wordId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public @ResponseBody GuessDTOResource<TeamDTO> updateWordById(@Nullable @PathVariable("wordId") Long id,@Nullable @RequestBody TeamDTO updatedObject) {
//        return wordService.updateWordById(id, updatedObject).orElseThrow(NotFoundException::new);
//    }

    //delete a word by id
    @Operation(summary = "Deletes a word", description = "It will delete word when provided id", tags = { "Word" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Word not found") })
    @DeleteMapping(value = "/{wordId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteWordById(@Nullable @PathVariable("wordId") Long id) {
        wordService.deleteWordById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
