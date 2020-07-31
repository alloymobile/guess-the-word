package com.alloymobile.guess.web;

import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.dto.GuessDTOResources;
import com.alloymobile.guess.service.impl.category.CategoryDTO;
import com.alloymobile.guess.service.impl.category.CategoryService;
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
@RequestMapping("/v1/api/category")
public class CategoryResource {

    private CategoryService categoryService;

    public CategoryResource(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //get one category by Id
    @Operation(summary = "Find category by ID", description = "Returns a single category", tags = { "Category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category not found") })
    @GetMapping(value = "/{categoryId}", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOResource<CategoryDTO> readCategoryById(@Nullable @PathVariable(name="categoryId") Long categoryId) {
        return categoryService.readCategoryById(categoryId).orElseThrow(NotFoundException::new);
    }

    //get category by description
    @Operation(summary = "Find category by name", description = "Returns a single category when a name is provided", tags = { "Category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category not found") })
    @GetMapping(value = "/name/{name}", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody GuessDTOResource<CategoryDTO> readCategoryByName(@Nullable @PathVariable(name="name") String categoryName) {
        return categoryService.readCategoryByName(categoryName).orElseThrow(NotFoundException::new);
    }

    //Get all category
    @Operation(summary = "Find all categorys", description = "Get all categorys", tags = { "Category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class)))) })
    @GetMapping( produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOResources<GuessDTOResource<CategoryDTO>> readAllCategory() {
        return categoryService.readAllCategory().orElseThrow(NotFoundException::new);
    }

    //add one category
    @Operation(summary = "Add a new category", description = "It will add a new category", tags = { "Category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Category already exists") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody GuessDTOResource<CategoryDTO> createCategory(@Nullable @RequestBody CategoryDTO newObject ) {
        return categoryService.createCategory(newObject).orElseThrow(NotFoundException::new);
    }

    //update a category by id
//    @Operation(summary = "Update an existing category", description = "It will update the category object", tags = { "Category" })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation"),
//            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
//            @ApiResponse(responseCode = "404", description = "Category not found"),
//            @ApiResponse(responseCode = "405", description = "Validation exception") })
//    @PutMapping(value = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public @ResponseBody GuessDTOResource<TeamDTO> updateCategoryById(@Nullable @PathVariable("categoryId") Long id,@Nullable @RequestBody TeamDTO updatedObject) {
//        return categoryService.updateCategoryById(id, updatedObject).orElseThrow(NotFoundException::new);
//    }

    //delete a category by id
    @Operation(summary = "Deletes a category", description = "It will delete category when provided id", tags = { "Category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Category not found") })
    @DeleteMapping(value = "/{categoryId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteCategoryById(@Nullable @PathVariable("categoryId") Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
