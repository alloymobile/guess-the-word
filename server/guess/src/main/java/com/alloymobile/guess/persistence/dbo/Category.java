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
@ToString(exclude = {"wordCategoryList"})
@EqualsAndHashCode(callSuper = false)
public class Category extends Type {

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "categoryWord")
    private List<Word> wordCategoryList;
}
