package com.alloymobile.guess.persistence.jpa;

import com.alloymobile.guess.persistence.dbo.Category;
import com.alloymobile.guess.persistence.dbo.Team;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface TeamRepository extends IGuessJpaRepository<Team> {
    Optional<Team> findByName(@NotNull String name);
}
