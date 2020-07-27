package com.alloymobile.guess.persistence.jpa;

import com.alloymobile.guess.persistence.dbo.Category;
import com.alloymobile.guess.persistence.dbo.Round;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface RoundRepository  extends IGuessJpaRepository<Round> {
    Optional<Round> findByName(@NotNull String name);
}
