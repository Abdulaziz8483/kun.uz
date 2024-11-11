package demo.kun.uz.controller;

import demo.kun.uz.dto.CommentDTO;
import demo.kun.uz.entity.CommentEntity;
import demo.kun.uz.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(name = "/api")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO createdComment = commentService.createComment(commentDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommentEntity> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        CommentEntity updatedComment = commentService.updateComment(id, commentDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/comments/article/{articleId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByArticleId(@PathVariable Long articleId) {
        List<CommentDTO> comments = commentService.getCommentsByArticleId(articleId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<Page<CommentDTO>> getAllComments(Pageable pageable) {
        Page<CommentDTO> commentPage = commentService.getAllComments(pageable);
        return new ResponseEntity<>(commentPage, HttpStatus.OK);
    }

    @GetMapping("/comments/filter")
    public ResponseEntity<Page<CommentDTO>> filterComments(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) LocalDateTime createdDateFrom,
            @RequestParam(required = false) LocalDateTime createdDateTo,
            @RequestParam(required = false) Long profileId,
            @RequestParam(required = false) Long articleId,
            Pageable pageable) {
        Page<CommentDTO> filteredComments = commentService.filterComments(id, createdDateFrom, createdDateTo, profileId, articleId, pageable);
        return new ResponseEntity<>(filteredComments, HttpStatus.OK);
    }
    @GetMapping("/comments/{id}/replies")
    public ResponseEntity<List<CommentDTO>> getRepliedComments(@PathVariable Long id) {
        List<CommentDTO> replies = commentService.getRepliedComments(id);
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }


}
