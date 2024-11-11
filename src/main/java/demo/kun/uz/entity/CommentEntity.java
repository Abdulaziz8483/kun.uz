package demo.kun.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(name = "reply_id")
    private Long replyId;

    @Column(name = "visible", nullable = false)
    private Boolean visible;

}
