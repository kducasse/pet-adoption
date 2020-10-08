package com.launchacademy.repositories;

import com.launchacademy.models.AdoptionApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication, Integer> {

}
