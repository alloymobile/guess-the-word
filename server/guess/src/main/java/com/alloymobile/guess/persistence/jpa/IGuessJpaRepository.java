package com.alloymobile.guess.persistence.jpa;

import com.alloymobile.guess.persistence.dbo.IGuessDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGuessJpaRepository<DBO_TYPE extends IGuessDBO> extends JpaRepository<DBO_TYPE, Long> , QuerydslPredicateExecutor<DBO_TYPE> {
}
