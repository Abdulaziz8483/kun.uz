package demo.kun.uz.repository;

import demo.kun.uz.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentEntity,Long> {
    List<CommentEntity> findByArticleId(Long articleId);

    List<CommentEntity> findByReplyId(Long replyId);

    List<CommentEntity> findByProfileId(Long profileId);

    Optional<CommentEntity> findById(Long id);

    void deleteById(Long id);

    List<CommentEntity> findByCreatedDateBetweenAndProfileIdAndArticleId(
            LocalDateTime createdDateFrom,
            LocalDateTime createdDateTo,
            Long profileId,
            Long articleId);

    List<CommentEntity> findByVisibleTrue();


    Page<CommentEntity> filterComments(Long id, LocalDateTime createdDateFrom, LocalDateTime createdDateTo, Long profileId, Long articleId, Pageable pageable);
}
