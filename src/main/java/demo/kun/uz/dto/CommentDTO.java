package demo.kun.uz.dto;

import demo.kun.uz.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String content;
    private Long profileId;
    private Long articleId;
    private Long replyId;
    private Boolean visible;



    public CommentDTO(CommentEntity comment) {
    }
}
