package demo.kun.uz.controller;

import demo.kun.uz.dto.RegionDTO;
import demo.kun.uz.entity.RegionEntity;
import demo.kun.uz.repository.RegionRepository;
import demo.kun.uz.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/region")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionRepository repository;
    @PostMapping("/create")
    public void create(@RequestBody RegionDTO regionDTO) {
      regionService.create(regionDTO);
    }

    @GetMapping("/get")
    public List<RegionEntity> getAll() {
        return regionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionEntity> getById(@PathVariable Long id) {
    Optional<RegionEntity> regionEntity = repository.findById(id);
    if (regionEntity.isPresent()){
        return ResponseEntity.ok(regionEntity.get());
    }
    return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public void update(@PathVariable Long id,
                       @RequestBody RegionDTO dto) {
        regionService.update(id, dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        regionService.delete(id);
    }

}

