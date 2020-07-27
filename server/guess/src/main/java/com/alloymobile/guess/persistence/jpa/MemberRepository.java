package com.alloymobile.guess.persistence.jpa;

import com.alloymobile.guess.persistence.dbo.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository  extends IGuessJpaRepository<Member> {
}
