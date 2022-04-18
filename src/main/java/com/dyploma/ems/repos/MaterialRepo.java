package com.dyploma.ems.repos;

import com.dyploma.ems.entity.Material;
import org.springframework.data.repository.CrudRepository;

public interface MaterialRepo extends CrudRepository<Material, Long> {
    Material findByMaterial(String material);
}
