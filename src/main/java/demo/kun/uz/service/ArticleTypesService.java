package demo.kun.uz.service;


import demo.kun.uz.dto.AttachDTO;
import demo.kun.uz.entity.ArticleTypesEntity;
import demo.kun.uz.repository.ArticleTypesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ArticleTypesService {

    private final ArticleTypesRepository articleTypesRepository;


    public void merge(String articleId, List<Integer> newIdList) {
        List<Integer> oldIdList = articleTypesRepository.findAllByArticleId(articleId);

        if (newIdList == null) {
            newIdList = new ArrayList<>();
        }
        for (Integer attachId : oldIdList) {
            if (!newIdList.contains(attachId)) {
                // delete operation {attachId}
                articleTypesRepository.deleteByArticleIdAndArticleTypeId(articleId, attachId);
            }
        }

        for (Integer newItemId : newIdList) {
            if (!oldIdList.contains(newItemId)) {
                // save
                ArticleTypesEntity articleTypes =new ArticleTypesEntity();
                articleTypes.setArticleId(articleId);
                articleTypes.setArticleTypeId(newItemId);
                articleTypesRepository.save(articleTypes);
            }
        }
    }

    private boolean exists(String attachId, List<AttachDTO> dtoList) {
        for (AttachDTO dto : dtoList) {
            if (dto.getId().equals(attachId)) {
                return true;
            }
        }
        return false;
    }


}

