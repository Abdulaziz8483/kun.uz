package demo.kun.uz.service;

import demo.kun.uz.dto.RegionDTO;
import demo.kun.uz.entity.RegionEntity;
import demo.kun.uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository repository;

    public RegionEntity create(RegionDTO dto){
        RegionEntity entity =new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setVisible(dto.getVisible());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setNameUz(dto.getNameUz());
        return repository.save(entity);
    }
    public List<RegionEntity> getAll(){
        return repository.findAll();

    }
    public RegionEntity getById(Long id){
        return repository.findById(id).get();
    }
    public RegionEntity update(Long id,RegionDTO dto){
        Optional<RegionEntity> optionalRegionEntity=repository.findById(id);
        if (optionalRegionEntity.isPresent()){
            RegionEntity entity=optionalRegionEntity.get();
            entity.setNameUz(dto.getNameUz());
            entity.setNameEn(dto.getNameEn());
            entity.setNameRu(dto.getNameRu());
            entity.setOrderNumber(dto.getOrderNumber());
            entity.setVisible(dto.getVisible());
            repository.save(entity);
        }
        return null;
    }
    public void delete(Long id){
        repository.deleteById(id);
    }

}
