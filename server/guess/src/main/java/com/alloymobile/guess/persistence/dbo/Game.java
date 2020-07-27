package com.alloymobile.guess.persistence.dbo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"roundGame","wordGame","teamGame"})
public class Game implements IGuessDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String score;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="ROUND_ID")
    private Round roundGame;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="WORD_ID")
    private Word wordGame;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="TEAM_ID")
    private Team teamGame;
}
