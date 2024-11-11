package demo.kun.uz.controller;

import demo.kun.uz.Enum.LanguageEnum;
import demo.kun.uz.dto.ArticleTypeDTO;
import demo.kun.uz.entity.ArticleTypeEntity;
import demo.kun.uz.mapper.ArticleTypeInfoMapper;
import demo.kun.uz.repository.ArticleTypeRepository;
import demo.kun.uz.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/article")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    @PostMapping("")
    public ResponseEntity<ArticleTypeDTO> create(@Valid @RequestBody ArticleTypeDTO articleTypeDTO) {
        return ResponseEntity.ok(articleTypeService.create(articleTypeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleTypeDTO> update(@PathVariable("id") Integer id,
                                                 @Valid @RequestBody ArticleTypeDTO articleTypeDTO) {
        return ResponseEntity.ok(articleTypeService.update(id, articleTypeDTO));
    }

    @GetMapping("")
    public ResponseEntity<Page<ArticleTypeDTO>> update(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(articleTypeService.getAll(page - 1, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleTypeService.delete(id));
    }

    @GetMapping("/lang")
    public ResponseEntity<List<ArticleTypeInfoMapper>> update(
            @RequestHeader(value = "Accept-language", defaultValue = "uz") LanguageEnum lang) {
        return ResponseEntity.ok(articleTypeService.getAllByLang(lang));
    }

}
