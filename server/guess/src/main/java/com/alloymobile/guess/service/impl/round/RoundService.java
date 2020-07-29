package com.alloymobile.guess.service.impl.round;

import com.alloymobile.guess.persistence.dbo.Round;
import com.alloymobile.guess.persistence.jpa.RoundRepository;
import com.alloymobile.guess.service.GuessService;
import com.alloymobile.guess.service.dto.GuessDTOPagedResources;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoundService extends GuessService<Round, RoundDTO> {

    public RoundService(RoundMapper roundMapper, RoundRepository roundRepository) {
        super(roundMapper, roundRepository);
    }

    public Optional<GuessDTOResource<RoundDTO>> readRoundById(@Nullable Long id){
        return super.readById(id);
    }

    public Optional<GuessDTOResource<RoundDTO>> readRoundByName(@Nullable String name){
        return ((RoundRepository)this.repository).findByName(name).map(this.mapper::toDTO);
    }

    public Optional<GuessDTOPagedResources<GuessDTOResource<RoundDTO>>> readAllRound(@Nullable Pageable pageable){
        return super.readAll(pageable);
    }
    
    public Optional<GuessDTOResource<RoundDTO>> createRound(@Nullable RoundDTO newObject) {
        return super.createOne(this.mapper.toNewDBO(newObject));
    }

//    public Optional<GuessDTOResource<TeamDTO>> updateRoundById(@Nullable Long id, @Nullable TeamDTO updatedObject) {
//        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
//    }

    public void deleteRoundById(@Nullable Long id) {
        super.deleteById(id);
    }
}
