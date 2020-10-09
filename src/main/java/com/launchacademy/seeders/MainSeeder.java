package com.launchacademy.seeders;

import com.launchacademy.repositories.AdoptablePetRepository;
import com.launchacademy.repositories.PetTypeRepository;
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
    Seeder.seed(petTypeRepo, PetTypeSeeder.getSeedData());
    Seeder.seedPets(petTypeRepo, adoptablePetRepo, AdoptablePetSeeder.getSeedData());
  }
}
