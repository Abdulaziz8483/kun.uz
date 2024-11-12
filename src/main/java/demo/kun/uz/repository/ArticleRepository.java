package demo.kun.uz.repository;

import demo.kun.uz.Enum.ArticleStatus;
import demo.kun.uz.dto.ArticleShortInfo;
import demo.kun.uz.entity.ArticleEntity;
import demo.kun.uz.mapper.ArticleShortInfoMapper;

import jakarta.persistence.criteria.From;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String>, PagingAndSortingRepository<ArticleEntity, String> {


    @Query(" select a.id as id,  a.title as title, a.description as description, a.imageId as imageId, a.publishedDate as publishedDate " +
            "  From ArticleEntity a  where  a.id not in ?2 and a.status =?1 and a.visible = true order by a.createdDate desc")
    List<ArticleShortInfoMapper> getLastIdNotIn(ArticleStatus status,
                                                List<String> excludeIdList, Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a ORDER BY a.createdDate DESC")
    List<ArticleEntity> findTop5LatestArticles(Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a ORDER BY a.createdDate DESC")
    List<ArticleEntity> findTop3LatestArticles(Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a WHERE a.status = :status ORDER BY a.publishedDate DESC")
    List<ArticleEntity> findTop4ByStatusOrderByPublishedDateDesc(ArticleStatus status);

    @Query("SELECT a FROM ArticleEntity a WHERE   a.region.id = :regionId ORDER BY a.publishedDate DESC")
    List<ArticleEntity> findTop5ByTypeAndRegionKeyOrderByPublishedDateDesc(Pageable pageable, Integer regionId);

    @Query("SELECT a FROM ArticleEntity a WHERE   a.category.id = :categoryId ORDER BY a.publishedDate DESC")
    List<ArticleEntity> findTop5ByCategoryKeyOrderByPublishedDateDesc( Integer categoryId);

    @Query("SELECT a FROM ArticleEntity a WHERE   a.region.id = :regionId ORDER BY a.publishedDate DESC")
    List<ArticleShortInfo> findTop5ByTypeAndRegionKey(Pageable pageable, Integer regionId);

    @Query("SELECT a FROM ArticleEntity a WHERE   a.region.id = :regionId ORDER BY a.publishedDate DESC")
    List<ArticleShortInfo> findTop5ByTypeAndCategoryId(Pageable pageable, Integer categoryId);


    @Transactional
    @Modifying
    @Query("UPDATE ArticleEntity a SET a.viewCount = a.viewCount + 1 WHERE a.id = :articleId")
    void increaseViewCount(@Param("articleId") String articleId);

    @Transactional
    @Modifying
    @Query("UPDATE ArticleEntity a SET a.sharedCount = a.sharedCount + 1 WHERE a.id = :articleId")
    void increaseShareCount(@Param("articleId") String articleId);
}
