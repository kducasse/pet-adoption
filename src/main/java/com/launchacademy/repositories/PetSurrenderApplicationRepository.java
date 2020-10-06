package com.launchacademy.repositories;

import com.launchacademy.models.PetSurrenderApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetSurrenderApplicationRepository extends CrudRepository<PetSurrenderApplication, Integer> {

}
