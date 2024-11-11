package demo.kun.uz.service;

import demo.kun.uz.dto.ArticleTypeDTO;
import demo.kun.uz.entity.ArticleTypeEntity;
import demo.kun.uz.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public void create(ArticleTypeDTO dto) {
        ArticleTypeEntity articleTypeEntity = new ArticleTypeEntity();
        articleTypeEntity.setOrderNumber(dto.getOrderNumber());
        articleTypeEntity.setVisible(dto.getVisible());
        articleTypeEntity.setNameRu(dto.getNameRu());
        articleTypeEntity.setNameEn(dto.getNameEn());
        articleTypeEntity.setNameUz(dto.getNameUz());
        articleTypeRepository.save(articleTypeEntity);
    }

    public List<ArticleTypeEntity> getAll() {
        return articleTypeRepository.findAll();
    }

    public Optional<ArticleTypeEntity> getById(Long id) {
        return articleTypeRepository.findById(id);
    }

    public ArticleTypeEntity update(Long id, ArticleTypeDTO dto) {
        Optional<ArticleTypeEntity> articleTypeEntity = articleTypeRepository.findById(id);

        if (articleTypeEntity.isPresent()) {

            articleTypeEntity.get().setOrderNumber(dto.getOrderNumber());
            articleTypeEntity.get().setNameUz(dto.getNameUz());
            articleTypeEntity.get().setNameRu(dto.getNameRu());
            articleTypeEntity.get().setNameEn(dto.getNameEn());
            articleTypeRepository.save(articleTypeEntity.get());
        }

        return null;
    }

    public void delete(Long id) {
        articleTypeRepository.deleteById(id);
    }

}
