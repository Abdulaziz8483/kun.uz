package demo.kun.uz.service;

import demo.kun.uz.dto.CommentDTO;
import demo.kun.uz.entity.CommentEntity;
import demo.kun.uz.exps.AppBadRequestException;
import demo.kun.uz.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

        public CommentDTO  createComment(CommentDTO commentDTO) {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setContent(commentDTO.getContent());
            commentEntity.setArticleId(commentDTO.getArticleId());
            commentEntity.setReplyId(commentDTO.getReplyId());
            commentEntity.setCreatedDate(LocalDateTime.now());
            commentEntity.setUpdateDate(LocalDateTime.now());

            CommentEntity savedComment = commentRepository.save(commentEntity);

            return new CommentDTO(savedComment);
        }


    public CommentEntity updateComment(Long id, CommentDTO commentDTO) {
        Optional<CommentEntity> Comment = commentRepository.findById(id);
        if (Comment.isPresent()) {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setContent(commentDTO.getContent());
            commentEntity.setUpdateDate(LocalDateTime.now());

            return  commentRepository.save(commentEntity);

        } else {
            throw new AppBadRequestException("Comment not found with id: " + id);
        }
    }

    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new AppBadRequestException("Comment not found with id: " + id);
        }
    }
    public List<CommentDTO> getCommentsByArticleId(Long articleId) {
        List<CommentEntity> comments = commentRepository.findByArticleId(articleId);
        List<CommentDTO> commentDTOs = new ArrayList<>();

        for (CommentEntity comment : comments) {
            CommentDTO commentDTO = new CommentDTO(comment);
            commentDTOs.add(commentDTO);
        }
        return commentDTOs;
    }
//    public Page<CommentDTO> getAllComments(Pageable pageable) {
//        Page<CommentEntity> commentPage = (Page<CommentEntity>) commentRepository.findAll(pageable);
//        return commentPage.map(CommentDTO::new);
//    }

    public Page<CommentDTO> filterComments(Long id, LocalDateTime createdDateFrom, LocalDateTime createdDateTo,
                                           Long profileId, Long articleId, Pageable pageable) {
        Page<CommentEntity> filteredComments = commentRepository.filterComments(id, createdDateFrom, createdDateTo, profileId, articleId, pageable);
        return filteredComments.map(CommentDTO::new);
    }

    public List<CommentDTO> getRepliedComments(Long commentId) {
        List<CommentEntity> replies = commentRepository.findByReplyId(commentId);
        return replies.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }






}
