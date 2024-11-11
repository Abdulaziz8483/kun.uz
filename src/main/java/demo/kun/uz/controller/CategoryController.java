package demo.kun.uz.controller;

import demo.kun.uz.dto.CategoryDTO;
import demo.kun.uz.dto.RegionDTO;
import demo.kun.uz.entity.CategoryEntity;
import demo.kun.uz.entity.RegionEntity;
import demo.kun.uz.repository.CategoryRepository;
import demo.kun.uz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/create")
    public void create(@RequestBody CategoryDTO categoryDTO) {

        categoryService.create(categoryDTO);
    }

    @GetMapping("/get")
    public List<CategoryEntity> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getById(@PathVariable Long id) {
        Optional<CategoryEntity> regionEntity = categoryRepository.findById(id);
        if (regionEntity.isPresent()){
            return ResponseEntity.ok(regionEntity.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public void update(@PathVariable Long id,
                       @RequestBody RegionDTO dto) {
        categoryService.update(id, dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }
}
