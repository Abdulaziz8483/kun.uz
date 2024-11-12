package demo.kun.uz.controller;

import demo.kun.uz.Enum.ArticleStatus;
import demo.kun.uz.dto.*;
import demo.kun.uz.entity.ArticleEntity;
import demo.kun.uz.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static demo.kun.uz.Enum.ProfileRole.MODERATOR;

@AllArgsConstructor
@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;


    @PostMapping("/create")
    public ResponseEntity<String> createArticle(@RequestBody ArticleDTO articleDTO) {
        String id = articleService.create(articleDTO);
        if (Objects.isNull(id)) {
            String responseBody = "Error creating article!";
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        String responseBody = "Success!";
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/update")
    public void updateArticle(@RequestBody ArticleUpdateDTO articleDto) {
        articleService.update(articleDto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ArticleGetDTO>> getArticles() {
        return new ResponseEntity<>(articleService.getAll(), HttpStatus.OK);


    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public void deleteArticle(@PathVariable String id) {
        articleService.delete(id);
    }

    @PatchMapping("/change-status/{id}")
    public ResponseEntity<Void> changeStatus(@PathVariable String id, @RequestParam ArticleStatus status) {
        articleService.ArticleStatusChange(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/last-5-by-types")
    public ResponseEntity<List<ArticleShortInfo>> getLast5ArticlesByTypes() {
        articleService.getLast5();

        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/last-3-by-types")
    public ResponseEntity<List<ArticleShortInfo>> getLast3ArticlesByTypes() {
        articleService.getLast3();
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/last-8-excluding")
    public ResponseEntity<List<ArticleShortInfo>> getLast8ArticlesExcluding(@RequestParam List<String> excludedIds) {

        articleService.getLast8(excludedIds);

        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}/lang")
    public ResponseEntity<ArticleFullInfo> getArticleByIdAndLang(@RequestBody ArticleFullInfo articleFullInfo) {
        articleService.getById(articleFullInfo.getId());
        return ResponseEntity.ok(new ArticleFullInfo());
    }

    @GetMapping("/last-4-by-types-excluding/{excludedId}")
    public ResponseEntity<List<ArticleShortInfo>> getLast4ArticlesByTypesExcluding(@RequestParam List<String> types) {
        articleService.getLast4(types);
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/most-read")
    public ResponseEntity<List<ArticleShortInfo>> getMostReadArticles() {

        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/api/articles/last4")
    public List<ArticleEntity> getLast4Articles(@RequestParam ArticleStatus status) {
        return articleService.getLast4Articles(status);
    }

    @GetMapping("/api/articles/last5")
    public List<ArticleEntity> getLast5Articles(@RequestParam Integer regionId) {
        return articleService.getLast5ArticlesByTypeAndRegion(regionId);
    }

    @GetMapping("/by-region")
    public ResponseEntity<List<ArticleShortInfo>> getArticlesByRegion(@RequestParam Integer regionId,
                                                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        articleService.getArticlesByRegion(regionId, page, size);
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/last-5-by-category")
    public ResponseEntity<List<ArticleShortInfo>> getLast5ArticlesByCategory(@RequestParam Integer categoryKey) {
        articleService.getLast5ArticlesByCategory(categoryKey);
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<ArticleShortInfo>> getArticlesByCategory(@RequestParam Integer categoryId,
                                                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                                                        @RequestParam(value = "size", defaultValue = "10") int size) {

        articleService.getArticlesByCategory(categoryId, page, size);
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping("/increase-view-count/{id}")
    public ResponseEntity<Void> increaseArticleViewCount(@PathVariable String articleId) {
        articleService.increaseArticleViewCount(articleId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/increase-share-count/{id}")
    public ResponseEntity<Void> increaseShareCount(@PathVariable String articleId) {
         articleService.increaseArticleShareCount(articleId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ArticleShortInfo>> filterArticles(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) UUID regionId,
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) LocalDateTime createdDateFrom,
            @RequestParam(required = false) LocalDateTime createdDateTo,
            @RequestParam(required = false) LocalDateTime publishedDateFrom,
            @RequestParam(required = false) LocalDateTime publishedDateTo,
            @RequestParam(required = false) UUID moderatorId,
            @RequestParam(required = false) UUID publisherId,
            @RequestParam(required = false) String status,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(new ArrayList<>());
    }


}

