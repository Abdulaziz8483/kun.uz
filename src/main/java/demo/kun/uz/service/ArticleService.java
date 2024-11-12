package demo.kun.uz.service;


import demo.kun.uz.Enum.ArticleStatus;
import demo.kun.uz.dto.ArticleDTO;
import demo.kun.uz.dto.ArticleGetDTO;
import demo.kun.uz.dto.ArticleShortInfo;
import demo.kun.uz.dto.ArticleUpdateDTO;
import demo.kun.uz.entity.ArticleEntity;
import demo.kun.uz.mapper.ArticleShortInfoMapper;
import demo.kun.uz.repository.ArticleRepository;
import demo.kun.uz.util.SpringSecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ArticleService {


    private final ArticleRepository articleRepository;
    private final ArticleTypesService articleTypesService;

    private final AttachService attachService;


    public String create(ArticleDTO articleDTO) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(articleDTO.getTitle());
        entity.setContent(articleDTO.getContent());
        entity.setDescription(articleDTO.getDescription());
        entity.setImageId(articleDTO.getImageId());
        entity.setRegionId(articleDTO.getRegionId());
        entity.setCategoryId(articleDTO.getCategoryId());
        entity.setSharedCount(0);
        entity.setViewCount(0);
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        entity.setVisible(true);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setModeratorId(SpringSecurityUtil.getCurrentUserId());
        //


        return articleRepository.save(entity).getId();
    }


    public void update(ArticleUpdateDTO articleUpdateDTO) {
        Optional<ArticleEntity> articleEntity = articleRepository.findById(articleUpdateDTO.getId());
        if (articleEntity.isPresent()) {
            articleEntity.get().setTitle(articleUpdateDTO.getTitle());
            articleEntity.get().setDescription(articleUpdateDTO.getDescription());
            articleEntity.get().setImageId(articleUpdateDTO.getImageId());
            articleEntity.get().setCategoryId(articleUpdateDTO.getCategoryId());
            articleEntity.get().setSharedCount(articleUpdateDTO.getSharedCount());
            articleRepository.save(articleEntity.get());
        }
    }

    public void delete(String id) {
        articleRepository.deleteById(id);
    }

    public void ArticleStatusChange(String id, ArticleStatus articleStatus) {
        Optional<ArticleEntity> articleEntity = articleRepository.findById(id);
        if (articleEntity.isPresent()) {
            articleEntity.get().setStatus(articleStatus);
            articleRepository.save(articleEntity.get());
        }

    }

    public List<ArticleEntity> getLast5() {
        Pageable pageable = PageRequest.of(0, 5);

        List<ArticleEntity> articleEntityList = articleRepository.findTop5LatestArticles(pageable);
        return articleEntityList;
    }

    public List<ArticleEntity> getLast3() {
        Pageable pageable = PageRequest.of(0, 3);
        List<ArticleEntity> articleEntityList = articleRepository.findTop3LatestArticles(pageable);
        return articleEntityList;
    }


    public List<ArticleShortInfo> getLast8(List<String> excludeIdList) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.getLastIdNotIn(
                ArticleStatus.PUBLISHED, excludeIdList,
                PageRequest.of(0, 8));
        return mapperList.stream().map(item -> toShortInfo(item)).toList();
    }

    public Optional<ArticleEntity> getById(String id) {
        Optional<ArticleEntity> articleEntityList = articleRepository.findById(id);
        return articleEntityList;
    }

    public List<ArticleShortInfo> getLast4(List<String> excludeIdList) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.getLastIdNotIn(ArticleStatus.PUBLISHED,
                excludeIdList,
                PageRequest.of(0, 4));
        return mapperList.stream().map(item -> toShortInfo(item)).toList();
    }

    public List<ArticleEntity> getLast4Articles(ArticleStatus status) {
        List<ArticleEntity> articles = articleRepository.findTop4ByStatusOrderByPublishedDateDesc(status);

        if (!articles.isEmpty()) {
            ArticleEntity latestArticle = articles.remove(0);
            articles.add(latestArticle);
        }
        return articles;
    }

    public List<ArticleEntity> getLast5ArticlesByTypeAndRegion(Integer regionId) {
        Pageable pageable = PageRequest.of(0, 5);

        return articleRepository.findTop5ByTypeAndRegionKeyOrderByPublishedDateDesc(pageable, regionId);
    }

    public List<ArticleEntity> getLast5ArticlesByCategory(Integer categoryId) {

        return articleRepository.findTop5ByCategoryKeyOrderByPublishedDateDesc(categoryId);
    }

    public List<ArticleShortInfo> getArticlesByRegion(Integer regionId,int page,int size){
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findTop5ByTypeAndRegionKey(pageable,regionId);

    }
    public List<ArticleShortInfo> getArticlesByCategory(Integer categoryId,int page,int size){
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findTop5ByTypeAndCategoryId(pageable,categoryId);

    }
    public void increaseArticleViewCount(String articleId){
     articleRepository.increaseViewCount(articleId);
    }
    public void increaseArticleShareCount(String articleId){
        articleRepository.increaseShareCount(articleId);
    }


    public ArticleShortInfo toShortInfo(ArticleShortInfoMapper mapper) {
        ArticleShortInfo dto = new ArticleShortInfo();
        dto.setId(mapper.getId());
        dto.setTitle(mapper.getTitle());
        dto.setDescription(mapper.getDescription());
        dto.setPublishedDate(mapper.getPublishDate());
        dto.setImage(attachService.getDTO(mapper.getImageId()));
        return dto;
    }


    public List<ArticleGetDTO> getAll() {
        Iterable<ArticleEntity> articleGetDTOS = articleRepository.findAll();
        List<ArticleGetDTO> dtos = new ArrayList<>();
        for (ArticleEntity articleEntity : articleGetDTOS) {
            ArticleGetDTO dto = new ArticleGetDTO();

            dto.setId(articleEntity.getId());
            dto.setTitle(articleEntity.getTitle());
            dto.setDescription(articleEntity.getDescription());
            dto.setContent(articleEntity.getContent());
            dto.setImageId(articleEntity.getImageId());
            dto.setRegionId(articleEntity.getRegionId());
            dto.setCategoryId(articleEntity.getCategoryId());

            dtos.add(dto);

        }
        return dtos;

    }


}

