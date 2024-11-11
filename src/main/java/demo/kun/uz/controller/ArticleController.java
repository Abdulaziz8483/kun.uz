package demo.kun.uz.controller;

import demo.kun.uz.dto.ArticleShortInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/articles")
public class ArticleController {



        @PostMapping("/create")
        public ResponseEntity<ArticleShortInfo> createArticle(@RequestBody ArticleShortInfo articleDto) {
            return ResponseEntity.ok(new ArticleShortInfo());
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<ArticleShortInfo> updateArticle(@PathVariable UUID id, @RequestBody DTO.ArticleShortInfo articleDto) {
            return ResponseEntity.ok(new ArticleShortInfo());
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteArticle(@PathVariable UUID id) {
            return ResponseEntity.noContent().build();
        }

        @PatchMapping("/change-status/{id}")
        public ResponseEntity<Void> changeStatus(@PathVariable UUID id, @RequestParam String status) {
            return ResponseEntity.ok().build();
        }

        @GetMapping("/last-5-by-types")
        public ResponseEntity<List<ArticleShortInfo>> getLast5ArticlesByTypes(@RequestParam List<String> types) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        @GetMapping("/last-3-by-types")
        public ResponseEntity<List<ArticleShortInfo>> getLast3ArticlesByTypes(@RequestParam List<String> types) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        @GetMapping("/last-8-excluding")
        public ResponseEntity<List<ArticleShortInfo>> getLast8ArticlesExcluding(@RequestParam List<UUID> excludedIds) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ArticleFullInfo> getArticleByIdAndLang(@PathVariable UUID id, @RequestParam String lang) {
            return ResponseEntity.ok(new ArticleFullInfo());
        }

        @GetMapping("/last-4-by-types-excluding/{excludedId}")
        public ResponseEntity<List<DTO.ArticleShortInfo>> getLast4ArticlesByTypesExcluding(@RequestParam List<String> types, @PathVariable UUID excludedId) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        @GetMapping("/most-read")
        public ResponseEntity<List<DTO.ArticleShortInfo>> getMostReadArticles() {
            return ResponseEntity.ok(new ArrayList<>());
        }

        @GetMapping("/last-4-by-tag")
        public ResponseEntity<List<DTO.ArticleShortInfo>> getLast4ArticlesByTagName(@RequestParam String tagName) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        @GetMapping("/last-5-by-types-and-region")
        public ResponseEntity<List<DTO.ArticleShortInfo>> getLast5ArticlesByTypesAndRegion(@RequestParam List<String> types, @RequestParam String regionKey) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        @GetMapping("/by-region")
        public ResponseEntity<List<DTO.ArticleShortInfo>> getArticlesByRegion(@RequestParam String regionKey, @RequestParam int page, @RequestParam int size) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        @GetMapping("/last-5-by-category")
        public ResponseEntity<List<DTO.ArticleShortInfo>> getLast5ArticlesByCategory(@RequestParam String categoryKey) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        @GetMapping("/by-category")
        public ResponseEntity<List<DTO.ArticleShortInfo>> getArticlesByCategory(@RequestParam String categoryKey, @RequestParam int page, @RequestParam int size) {


            return ResponseEntity.ok(new ArrayList<>());
        }

        @PostMapping("/increase-view-count/{id}")
        public ResponseEntity<Void> increaseArticleViewCount(@PathVariable UUID id) {
            return ResponseEntity.ok().build();
        }

        @PostMapping("/increase-share-count/{id}")
        public ResponseEntity<Void> increaseShareCount(@PathVariable UUID id) {
            return ResponseEntity.ok().build();
        }

        @GetMapping("/filter")
        public ResponseEntity<List<DTO.ArticleShortInfo>> filterArticles(
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

