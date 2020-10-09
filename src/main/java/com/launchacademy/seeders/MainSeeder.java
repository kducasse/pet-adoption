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

  private PetTypeRepository petTypeRepo;
  private AdoptablePetRepository adoptablePetRepo;

  @Autowired
  public MainSeeder(PetTypeRepository petTypeRepo, AdoptablePetRepository adoptablePetRepo) {
    this.petTypeRepo = petTypeRepo;
    this.adoptablePetRepo = adoptablePetRepo;
  }

  @Override
  public void run(String... args) throws Exception {
    if (Lists.newArrayList(petTypeRepo.findAll()).isEmpty()) {
      Seeder.seed(petTypeRepo, PetTypeSeeder.getSeedData());
    }

    if (Lists.newArrayList(adoptablePetRepo.findAll()).isEmpty()) {
      PetType twoLegged = petTypeRepo.findByTypeIgnoreCase("Two-legged");
      PetType fourLegged = petTypeRepo.findByTypeIgnoreCase("Four-legged");

      Map<AdoptablePet, String> adoptablePets = AdoptablePetSeeder.getSeedData();
      for (AdoptablePet pet : adoptablePets.keySet()) {
        if (adoptablePets.get(pet).equals("two legged")) {
          pet.setPetType(twoLegged);
        } else {
          pet.setPetType(fourLegged);
        }
      }
      Seeder.seed(adoptablePetRepo, adoptablePets.keySet());
    }
  }
}
