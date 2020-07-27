package com.alloymobile.guess.persistence.jpa;

import com.alloymobile.guess.persistence.dbo.Word;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends IGuessJpaRepository<Word> {
}
