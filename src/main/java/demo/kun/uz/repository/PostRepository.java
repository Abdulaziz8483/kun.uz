package demo.kun.uz.repository;

import demo.kun.uz.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {

}
