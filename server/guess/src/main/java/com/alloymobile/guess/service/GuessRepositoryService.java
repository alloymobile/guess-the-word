package com.alloymobile.guess.service;

import com.alloymobile.guess.persistence.dbo.IGuessDBO;
import com.alloymobile.guess.persistence.jpa.IGuessJpaRepository;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class GuessRepositoryService<DBO_TYPE extends IGuessDBO> {

    /**
     * Variable used to access the database and perform all the queries
     */
    @Getter
    protected IGuessJpaRepository<DBO_TYPE> repository;


    public GuessRepositoryService(IGuessJpaRepository<DBO_TYPE> repository) {
        this.repository = repository;
    }

    //Convert the database response to optional
    public <R, T extends Iterable<R>> Optional<T> makeOptional(@Nullable T iterable) {
        if (iterable != null && iterable.iterator().hasNext()) {
            return Optional.of(iterable);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Used to find all the objects to the related to the specific page
     *
     * @param pageable the page number and size
     * @return returns the object in optional form
     */
    public Optional<Page<DBO_TYPE>> findAll( @Nullable Pageable pageable) {
        if (null == pageable) {
            return Optional.empty();
        }
        return this.makeOptional(this.repository.findAll(pageable));
    }

    /**
     * Used to find an object based on the givrn id
     *
     * @param id the id of the object entity
     * @return returns the optional form of the object
     */
    public Optional<DBO_TYPE> findById(@Nullable Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return this.repository.findById(id);
    }

    /**
     * Save an array of objects to the database
     *
     * @param iterable the list of all the objects
     * @return optional of all the saved objects
     */
    public Optional<List<DBO_TYPE>> save(@Nullable Iterable<DBO_TYPE> iterable) {
        if (iterable == null || !iterable.iterator().hasNext()) {
            return Optional.empty();
        }
        return this.makeOptional(this.repository.saveAll(iterable));
    }

    /**
     * Save a single object to the database if not present else will create the object
     *
     * @param dbo_type the object
     * @return returns the optional of saved object
     */
    public Optional<DBO_TYPE> save(@Nullable DBO_TYPE dbo_type) {
        if (dbo_type == null) {
            return Optional.empty();
        }
        return Optional.of(this.repository.save(dbo_type));
    }

    /**
     * Delete object by id first will find the object if present then will call the
     * delete method to delete
     *
     * @param aLong the id of the object to be deleted
     */
    public void deleteById(@Nullable Long aLong) {
        if (aLong != null) {
            this.repository.findById(aLong).ifPresent(this::delete);
        }
    }

    /**
     * method delete to delete the object
     *
     * @param dbo_type the object to be deleted
     */
    public void delete(@Nullable DBO_TYPE dbo_type) {
        if (dbo_type != null) {
            this.repository.delete(dbo_type);
        }
    }

}