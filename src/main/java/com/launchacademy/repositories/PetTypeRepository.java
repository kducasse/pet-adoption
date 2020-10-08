package com.launchacademy.repositories;

import com.launchacademy.models.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Integer> {
  PetType findByTypeIgnoreCase(String type);
}
