package demo.kun.uz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ArticleGetDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private String imageId;
    private Integer regionId;
    private Integer categoryId;
    private List<Integer> articleTypesList;
}
