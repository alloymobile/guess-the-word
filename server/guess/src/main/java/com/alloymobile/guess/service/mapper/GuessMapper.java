package com.alloymobile.guess.service.mapper;


import com.alloymobile.guess.exception.InternalServerException;
import com.alloymobile.guess.exception.NotFoundException;
import com.alloymobile.guess.persistence.dbo.IGuessDBO;
import com.alloymobile.guess.service.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class GuessMapper<DBO_TYPE extends IGuessDBO, DTO_TYPE extends IGuessDTO> {

    protected abstract void populateDBO(@NotNull DBO_TYPE dbo, @NotNull DTO_TYPE dto);

    @NotNull
    protected abstract DTO_TYPE toDTOImpl(@NotNull DBO_TYPE dbo);

    @NotNull
    public DBO_TYPE toNewDBO(@NotNull DTO_TYPE dto) {
        final DBO_TYPE newDBOInstance = this.getNewDBOInstance();
        this.populateDBO(newDBOInstance, dto);
        return newDBOInstance;
    }

    @NotNull
    public GuessDTOResource<DTO_TYPE> toDTO(@NotNull DBO_TYPE dbo) {
        final GuessDTOResource<DTO_TYPE> dto = new GuessDTOResource<>(toDTOImpl(dbo));
        return dto;
    }

    @NotNull
    @Size(min = 1)
    public GuessDTOResources<GuessDTOResource<DTO_TYPE>> toDTOs(@NotNull @Size(min = 1) List<DBO_TYPE> dbos) {
        final List<GuessDTOResource<DTO_TYPE>> collect = dbos.stream().map(this::toDTO).collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new NotFoundException();
        }
        return new GuessDTOResources<>(collect);
    }

    @NotNull
    @Size(min = 1)
    public GuessDTOPagedResources<GuessDTOResource<DTO_TYPE>> toDTOs(@NotNull @Size(min = 1) Page<DBO_TYPE> dbos) {
        final List<GuessDTOResource<DTO_TYPE>> collect = dbos.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new NotFoundException();
        }
        return new GuessDTOPagedResources<>(collect, dbos);
    }

    @NotNull
    private DBO_TYPE getNewDBOInstance() {
        try {
            return getDBOClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Unable to instantiate class from mapper", e);
            throw new InternalServerException("Unable to instantiate class from mapper");
        }
    }

    private Class<DBO_TYPE> getDBOClass() {
        final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            return (Class<DBO_TYPE>) Class.forName(type.getTypeName());
        } catch (ClassNotFoundException e) {
            log.error("Unable to get DBO type from mapper", e);
            throw new InternalServerException("Unable to get DBO type from mapper");
        }
    }
}
