package com.launchacademy.seeders;

import com.google.common.collect.Lists;
import com.launchacademy.models.AdoptablePet;
import com.launchacademy.models.PetType;
import com.launchacademy.repositories.AdoptablePetRepository;
import com.launchacademy.repositories.PetTypeRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {

  private PetTypeRepository petTypeRepository;
  private AdoptablePetRepository adoptablePetRepository;

  @Autowired
  public MainSeeder(PetTypeRepository petTypeRepository, AdoptablePetRepository adoptablePetRepository) {
    this.petTypeRepository = petTypeRepository;
    this.adoptablePetRepository = adoptablePetRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    if (Lists.newArrayList(petTypeRepository.findAll()).isEmpty()) {
      Seeder.seed(petTypeRepository, PetTypeSeeder.getSeedData());
    }

    if (Lists.newArrayList(adoptablePetRepository.findAll()).isEmpty()) {
      PetType twoLegged = petTypeRepository.findByTypeIgnoreCase("Two-legged");
      PetType fourLegged = petTypeRepository.findByTypeIgnoreCase("Four-legged");

      Map<AdoptablePet, String> adoptablePets = AdoptablePetSeeder.getSeedData();
      for (AdoptablePet pet : adoptablePets.keySet()) {
        if(adoptablePets.get(pet).equals("two legged")) {
          pet.setPetType(twoLegged);
        }else {
          pet.setPetType(fourLegged);
        }
      }
      Seeder.seed(adoptablePetRepository, adoptablePets.keySet());
    }
  }
}
