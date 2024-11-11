package demo.kun.uz.service;


import demo.kun.uz.Enum.ArticleStatus;
import demo.kun.uz.dto.ArticleDTO;
import demo.kun.uz.dto.ArticleShortInfo;
import demo.kun.uz.mapper.ArticleShortInfoMapper;
import demo.kun.uz.repository.ArticleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AttachService attachService;
    public ArticleDTO create(ArticleDTO articleDTO) {
      return articleDTO;
    }


    public List<ArticleShortInfo> getLast8(List<String> excludeIdList){
        List<ArticleShortInfoMapper> mapperList = articleRepository.getLastIdNotIn(
                ArticleStatus.PUBLISHED,excludeIdList,
                PageRequest.of(0,8));
        return mapperList.stream().map(item->toShortInfo(item)).toList();
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

}

