package com.alloymobile.guess.service.impl.member;

import com.alloymobile.guess.persistence.dbo.Member;
import com.alloymobile.guess.persistence.jpa.MemberRepository;
import com.alloymobile.guess.persistence.jpa.IGuessJpaRepository;
import com.alloymobile.guess.service.GuessService;
import com.alloymobile.guess.service.dto.GuessDTOPagedResources;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.mapper.GuessMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MemberService extends GuessService<Member, MemberDTO> {

    public MemberService(MemberMapper membersMapper, MemberRepository memberRepository) {
        super(membersMapper, memberRepository);
    }

    public Optional<GuessDTOResource<MemberDTO>> readMemberById(@Nullable Long id){
        return super.readById(id);
    }

    public Optional<GuessDTOPagedResources<GuessDTOResource<MemberDTO>>> readAllMember(@Nullable Predicate predicate, @Nullable Pageable pageable){
        return super.readAll(predicate,pageable);
    }
    
    public Optional<GuessDTOResource<MemberDTO>> createMember(@Nullable MemberDTO newObject) {
        return super.createOne(this.mapper.toNewDBO(newObject));
    }

//    public Optional<GuessDTOResource<TeamDTO>> updateMemberById(@Nullable Long id, @Nullable TeamDTO updatedObject) {
//        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
//    }

    public void deleteMemberById(@Nullable Long id) {
        super.deleteById(id);
    }
}
