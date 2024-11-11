package demo.kun.uz.dto;


import demo.kun.uz.Enum.ProfileRole;
import demo.kun.uz.Enum.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private Integer photoId;
    private LocalDateTime createdDate;
    private String jwtToken;

}
