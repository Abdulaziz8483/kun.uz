package demo.kun.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class PostAttachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "post_id")
    private Integer postId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", insertable =false, updatable =false)
    private PostEntity post;

    @Column(name = "attach_id")
    private String attachId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private AttachEntity attach;
}
