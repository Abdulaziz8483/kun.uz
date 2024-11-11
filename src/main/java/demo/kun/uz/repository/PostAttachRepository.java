package demo.kun.uz.repository;

import demo.kun.uz.entity.PostAttachEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostAttachRepository extends CrudRepository<PostAttachEntity,Integer> {

    @Query("select attachId from PostAttachEntity where postId =?1")
    List<String> findAllByPostId(Integer postId);

    @Modifying
    @Transactional
    @Query("delete from PostAttachEntity where postId =?1")
    void deleteByPostId(Integer postId);

    @Modifying
    @Transactional
    @Query("delete from PostAttachEntity where postId =?1 and attachId = ?2")
    void deleteByPostIdAndAttachId(Integer postId, String attachId);
}
