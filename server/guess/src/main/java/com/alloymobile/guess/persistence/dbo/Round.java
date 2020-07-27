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
//@ToString(exclude = {"gameRoundList"})
@EqualsAndHashCode(callSuper = false)
public class Round extends Type {

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "roundGame")
    private List<Game> gameRoundList;
}
