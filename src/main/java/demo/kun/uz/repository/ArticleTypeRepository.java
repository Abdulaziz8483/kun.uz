package demo.kun.uz.repository;

import demo.kun.uz.entity.ArticleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity,Long> {
}
