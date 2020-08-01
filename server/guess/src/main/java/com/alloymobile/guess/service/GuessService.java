package com.alloymobile.guess.service;

import com.alloymobile.guess.persistence.dbo.IGuessDBO;
import com.alloymobile.guess.persistence.jpa.IGuessJpaRepository;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.dto.GuessDTOResources;
import com.alloymobile.guess.service.dto.IGuessDTO;
import com.alloymobile.guess.service.mapper.GuessMapper;
import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class GuessService <DBO_TYPE extends IGuessDBO, DTO_TYPE extends IGuessDTO> extends GuessRepositoryService<DBO_TYPE> {

    @Getter
    protected final GuessMapper<DBO_TYPE, DTO_TYPE> mapper;

    protected GuessService(GuessMapper<DBO_TYPE, DTO_TYPE> GuessMapper
            , IGuessJpaRepository<DBO_TYPE> repository) {
        super(repository);
        this.mapper = GuessMapper;
    }

    protected Optional<GuessDTOResource<DTO_TYPE>> readById(@Nullable Long id) {
        return super.findById(id).map(this.mapper::toDTO);
    }

    protected Optional<GuessDTOResources<GuessDTOResource<DTO_TYPE>>> readAll() {
        return super.findAll().map(dbos->this.getMapper().toDTOs(dbos));
    }

    protected Optional<GuessDTOResource<DTO_TYPE>> createOne(@Nullable DBO_TYPE dbo_type) {
        return super.save(dbo_type).map(this.mapper::toDTO);
    }

    public Optional<GuessDTOResources<GuessDTOResource<DTO_TYPE>>> createMany(@Nullable List<DBO_TYPE> dbo_type) {
        return super.save(dbo_type).map(this.mapper::toDTOs);
    }

//    protected Optional<GuessDTOResource<DTO_TYPE>> update(@Nullable DBO_TYPE dboToUpdate, @Nullable DTO_TYPE updatedObject) {
//        if (dboToUpdate == null || updatedObject == null) {
//            return Optional.empty();
//        }
//        this.mapper.extendForUpdate(Collections.singletonList(new GuessMapperPair<>(dboToUpdate, updatedObject)));
//        return this.save(dboToUpdate).map(this.mapper::toDTO);
//    }
//
//    private Optional<List<DBO_TYPE>> updateGuessMapperPair(@Nullable Collection<GuessMapperPair<DBO_TYPE, DTO_TYPE>> updates) {
//        if (updates == null || updates.isEmpty()) {
//            return Optional.empty();
//        }
//        this.getMapper().extendForUpdate(updates);
//        return this.save(updates.stream().map(update -> update.dbo).collect(Collectors.toList()));
//    }
//
//    protected Optional<DBO_TYPE> updateGuessMapperPair(@Nullable GuessMapperPair<DBO_TYPE, DTO_TYPE> update) {
//        if (update == null) {
//            return Optional.empty();
//        }
//        return this.updateGuessMapperPair(Collections.singletonList(update)).map(Collection::stream).flatMap(Stream::findFirst);
//    }
}