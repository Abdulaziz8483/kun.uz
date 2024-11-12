package demo.kun.uz.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ArticleFullInfo {
    private String id; // UUID
    private String title;
    private String description;
    private String content;
    private int sharedCount;
    private RegionDTO region;
    private CategoryDTO category;
    private LocalDateTime publishedDate;
    private int viewCount;
    private int likeCount;
    private List<String> tagList;



}


