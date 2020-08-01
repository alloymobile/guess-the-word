package com.alloymobile.guess.service.impl.category;

import com.alloymobile.guess.persistence.dbo.Category;
import com.alloymobile.guess.persistence.jpa.CategoryRepository;
import com.alloymobile.guess.service.GuessService;
import com.alloymobile.guess.service.dto.GuessDTOResource;
import com.alloymobile.guess.service.dto.GuessDTOResources;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryService extends GuessService<Category,CategoryDTO> {

    public CategoryService(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        super(categoryMapper, categoryRepository);
    }

    public Optional<GuessDTOResource<CategoryDTO>> readCategoryById(@Nullable Long id){
        return super.readById(id);
    }

    public Optional<GuessDTOResource<CategoryDTO>> readCategoryByName(@Nullable String name){
        return ((CategoryRepository)this.repository).findByName(name).map(this.mapper::toDTO);
    }

    public Optional<GuessDTOResources<GuessDTOResource<CategoryDTO>>> readAllCategory(){
        return super.readAll();
    }
    
    public Optional<GuessDTOResource<CategoryDTO>> createCategory(@Nullable CategoryDTO newObject) {
        return super.createOne(this.mapper.toNewDBO(newObject));
    }

//    public Optional<GuessDTOResource<TeamDTO>> updateCategoryById(@Nullable Long id, @Nullable TeamDTO updatedObject) {
//        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
//    }

    public void deleteCategoryById(@Nullable Long id) {
        super.deleteById(id);
    }
}
