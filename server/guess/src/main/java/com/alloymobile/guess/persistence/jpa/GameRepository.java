package com.alloymobile.guess.persistence.jpa;

import com.alloymobile.guess.persistence.dbo.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository  extends IGuessJpaRepository<Game> {
}
