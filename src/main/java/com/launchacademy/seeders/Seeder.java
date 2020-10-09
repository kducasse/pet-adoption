package com.launchacademy.seeders;

import com.google.common.collect.Lists;
import com.launchacademy.models.AdoptablePet;
import com.launchacademy.models.PetType;
import com.launchacademy.repositories.AdoptablePetRepository;
import com.launchacademy.repositories.PetTypeRepository;
import java.util.Collection;
import java.util.Map;
import org.springframework.data.repository.CrudRepository;

public class Seeder {

  static public <U, T extends CrudRepository<U, Integer>> void seed(T repository, Collection<U> seedData) {
    if (Lists.newArrayList(repository.findAll()).isEmpty()) {
      for (U data: seedData) {
        repository.save(data);
      }
    }
  }

  static public void seedPets(PetTypeRepository petTypeRepo, AdoptablePetRepository adoptablePetRepo,
      Map<AdoptablePet, String> adoptablePets) {
    if(Lists.newArrayList(adoptablePetRepo.findAll()).isEmpty()) {
      PetType twoLegged = petTypeRepo.findByTypeIgnoreCase("Two-legged");
      PetType fourLegged = petTypeRepo.findByTypeIgnoreCase("Four-legged");

      for (AdoptablePet pet : adoptablePets.keySet()) {
        if (adoptablePets.get(pet).equals("two legged")) {
          pet.setPetType(twoLegged);
        } else {
          pet.setPetType(fourLegged);
        }
      }
    }
    seed(adoptablePetRepo, adoptablePets.keySet());
  }
}
