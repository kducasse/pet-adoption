package com.launchacademy.repositories;

import com.launchacademy.models.AdoptablePet;
import com.launchacademy.models.PetType;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptablePetRepository extends CrudRepository<AdoptablePet, Integer> {

    Optional<AdoptablePet> findByIdAndPetType(Integer id, PetType PetType);
}
