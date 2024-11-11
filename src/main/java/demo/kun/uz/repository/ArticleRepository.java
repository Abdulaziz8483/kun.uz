package demo.kun.uz.repository;

import demo.kun.uz.Enum.ArticleStatus;
import demo.kun.uz.entity.ArticleEntity;
import demo.kun.uz.mapper.ArticleShortInfoMapper;
import jakarta.persistence.Id;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<ArticleEntity,Long>, PagingAndSortingRepository<ArticleEntity,> {

    @Query(" select a.id as id,  a.title as title, a.description as description, a.imageId as imageId, a.publishedDate as publishedDate " +
            "  From ArticleEntity a  where  a.id not in ?2 and a.status =?1 and a.visible = true order by a.createdDate desc")
    List<ArticleShortInfoMapper> getLastIdNotIn(ArticleStatus status,
                                                List<String> excludeIdList,
                                                Pageable pageable);

}
