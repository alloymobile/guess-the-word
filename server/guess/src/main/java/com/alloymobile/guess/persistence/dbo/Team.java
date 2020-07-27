package com.alloymobile.guess.persistence.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"memberTeamList","GameTeamList"})
@EqualsAndHashCode(callSuper = false)
public class Team extends Type {

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "teamMember")
    private List<Member> memberTeamList;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "teamGame")
    private List<Game> GameTeamList;
}
