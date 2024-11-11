package demo.kun.uz.entity;

import demo.kun.uz.Enum.ProfileRole;
import demo.kun.uz.Enum.ProfileStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Profile")
@Getter
@Setter
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private ProfileStatus statusEnum;

    @Enumerated(EnumType.STRING)
    private ProfileRole roleEnum;

    private boolean visible;

    private LocalDateTime createdDate;

    private Long photoId;



}

