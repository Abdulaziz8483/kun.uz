package demo.kun.uz.service;

import demo.kun.uz.dto.CategoryDTO;
import demo.kun.uz.dto.RegionDTO;
import demo.kun.uz.entity.CategoryEntity;
import demo.kun.uz.entity.RegionEntity;
import demo.kun.uz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity create(CategoryDTO dto){
        CategoryEntity entity =new CategoryEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setVisible(dto.getVisible());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setNameUz(dto.getNameUz());
        return categoryRepository.save(entity);
    }
    public List<CategoryEntity> getAll(){
        return categoryRepository.findAll();

    }
    public CategoryEntity getById(Long id){
        return categoryRepository.findById(id).get();
    }
    public RegionEntity update(Long id,RegionDTO dto){
        Optional<CategoryEntity> optionalCategoryEntity=categoryRepository.findById(id);
        if (optionalCategoryEntity.isPresent()){
            CategoryEntity entity=optionalCategoryEntity.get();
            entity.setNameUz(dto.getNameUz());
            entity.setNameEn(dto.getNameEn());
            entity.setNameRu(dto.getNameRu());
            entity.setOrderNumber(dto.getOrderNumber());
            entity.setVisible(dto.getVisible());
            categoryRepository.save(entity);
        }
        return null;
    }
    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
}
