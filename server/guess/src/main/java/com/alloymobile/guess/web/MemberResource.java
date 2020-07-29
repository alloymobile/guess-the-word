package com.alloymobile.guess.web;

import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.persistence.dbo.Member;
import com.alloymobile.guess.service.dto.GuessDTOPagedResources;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.impl.member.MemberDTO;
import com.alloymobile.guess.service.impl.member.MemberService;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/member")
public class MemberResource {

    private MemberService memberService;

    public MemberResource(MemberService memberService){
        this.memberService = memberService;
    }

    //get one member by Id
    @Operation(summary = "Find member by ID", description = "Returns a single member", tags = { "Member" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "404", description = "Member not found") })
    @GetMapping(value = "/{memberId}", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOResource<MemberDTO> readMemberById(@Nullable @PathVariable(name="memberId") Long memberId) {
        return memberService.readMemberById(memberId).orElseThrow(NotFoundException::new);
    }

    //Get all member
    @Operation(summary = "Find all members", description = "Get all members", tags = { "Member" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MemberDTO.class)))) })
    @GetMapping( produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody
    GuessDTOPagedResources<GuessDTOResource<MemberDTO>> readAllMember(@QuerydslPredicate(root = Member.class) Predicate predicate , @Nullable Pageable pageable ) {
        return memberService.readAllMember(predicate,pageable).orElseThrow(NotFoundException::new);
    }

    //add one member
    @Operation(summary = "Add a new member", description = "It will add a new member", tags = { "Member" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Member created",
                    content = @Content(schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Member already exists") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody GuessDTOResource<MemberDTO> createMember(@Nullable @RequestBody MemberDTO newObject ) {
        return memberService.createMember(newObject).orElseThrow(NotFoundException::new);
    }

    //update a member by id
//    @Operation(summary = "Update an existing member", description = "It will update the member object", tags = { "Member" })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation"),
//            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
//            @ApiResponse(responseCode = "404", description = "Member not found"),
//            @ApiResponse(responseCode = "405", description = "Validation exception") })
//    @PutMapping(value = "/{memberId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public @ResponseBody GuessDTOResource<TeamDTO> updateMemberById(@Nullable @PathVariable("memberId") Long id,@Nullable @RequestBody TeamDTO updatedObject) {
//        return memberService.updateMemberById(id, updatedObject).orElseThrow(NotFoundException::new);
//    }

    //delete a member by id
    @Operation(summary = "Deletes a member", description = "It will delete member when provided id", tags = { "Member" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Member not found") })
    @DeleteMapping(value = "/{memberId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteMemberById(@Nullable @PathVariable("memberId") Long id) {
        memberService.deleteMemberById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
