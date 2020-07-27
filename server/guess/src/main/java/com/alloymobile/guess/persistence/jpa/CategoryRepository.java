package com.alloymobile.guess.persistence.jpa;

import com.alloymobile.guess.persistence.dbo.Category;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface CategoryRepository  extends IGuessJpaRepository<Category> {
    Optional<Category> findByName(@NotNull String name);
}
