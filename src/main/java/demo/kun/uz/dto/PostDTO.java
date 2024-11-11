package demo.kun.uz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PostDTO {
    private int id;
    private String title;
    private String content;
    private List<AttachDTO> attachDTOList;
}
