package com.launchacademy.repositories;

import com.launchacademy.models.AdoptablePet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptablePetRepository extends CrudRepository<AdoptablePet, Integer> {

}
