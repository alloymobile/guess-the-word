package com.alloymobile.guess.service.impl.team;

import com.alloymobile.guess.persistence.dbo.Team;
import com.alloymobile.guess.persistence.jpa.TeamRepository;
import com.alloymobile.guess.service.GuessService;
import com.alloymobile.guess.service.dto.GuessDTOPagedResources;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TeamService extends GuessService<Team, TeamDTO> {

    public TeamService(TeamMapper teamMapper, TeamRepository teamRepository) {
        super(teamMapper, teamRepository);
    }

    public Optional<GuessDTOResource<TeamDTO>> readTeamById(@Nullable Long id){
        return super.readById(id);
    }

    public Optional<GuessDTOResource<TeamDTO>> readTeamByName(@Nullable String name){
        return ((TeamRepository)this.repository).findByName(name).map(this.mapper::toDTO);
    }

    public Optional<GuessDTOPagedResources<GuessDTOResource<TeamDTO>>> readAllTeam(@Nullable Pageable pageable){
        return super.readAll(pageable);
    }
    
    public Optional<GuessDTOResource<TeamDTO>> createTeam(@Nullable TeamDTO newObject) {
        return super.createOne(this.mapper.toNewDBO(newObject));
    }

//    public Optional<GuessDTOResource<TeamDTO>> updateTeamById(@Nullable Long id, @Nullable TeamDTO updatedObject) {
//        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
//    }

    public void deleteTeamById(@Nullable Long id) {
        super.deleteById(id);
    }
}
