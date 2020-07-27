package com.alloymobile.guess.service.impl.member;

import com.alloymobile.guess.exception.InternalServerException;
import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.persistence.dbo.Member;
import com.alloymobile.guess.service.impl.team.TeamService;
import com.alloymobile.guess.service.mapper.GuessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class MemberMapper extends GuessMapper<Member, MemberDTO> {

    @Autowired
    TeamService teamService;

    @Override
    protected void populateDBO(@NotNull Member dbo, @NotNull MemberDTO dto) {
        if(dto.getId() != null) {
            dbo.setId(dto.getId());
        }
        dbo.setName(dto.getName());
        if(dto.getTeamId() != null){
            dbo.setTeamMember(teamService.findById(dto.getTeamId()).orElseThrow(NotFoundException::new));;
        }else{
            throw new InternalServerException("Must provide Team id");
        }
    }

    @Override
    protected MemberDTO toDTOImpl(@NotNull Member dbo) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(dbo.getId());
        memberDTO.setName(dbo.getName());
        memberDTO.setTeamId(dbo.getTeamMember().getId());
        return memberDTO;
    }

}
