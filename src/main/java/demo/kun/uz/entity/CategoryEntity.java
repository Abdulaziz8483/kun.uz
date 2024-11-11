package demo.kun.uz.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "Category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "name_uz", length = 255)
    private String nameUz;

    @Column(name = "name_ru", length = 255)
    private String nameRu;

    @Column(name = "name_en", length = 255)
    private String nameEn;

    @Column(name = "visible")
    private Boolean visible = true;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


}

