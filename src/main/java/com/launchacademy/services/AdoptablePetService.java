package com.launchacademy.services;

import com.launchacademy.models.AdoptablePet;
import com.launchacademy.models.AdoptionApplication;
import com.launchacademy.models.PetSurrenderApplication;
import com.launchacademy.repositories.AdoptablePetRepository;
import com.launchacademy.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptablePetService {

  private AdoptablePetRepository adoptablePetRepo;
  private PetTypeRepository petTypeRepo;

  @Autowired
  private AdoptablePetService(AdoptablePetRepository adoptablePetRepo,
      PetTypeRepository petTypeRepo) {
    this.adoptablePetRepo = adoptablePetRepo;
    this.petTypeRepo = petTypeRepo;
  }

  public AdoptablePet processNewPet(PetSurrenderApplication surrenderApp) {
    AdoptablePet newPet = new AdoptablePet();
    newPet.setName(surrenderApp.getPetName());
    newPet.setImgUrl(surrenderApp.getImgUrl());
    newPet.setAge(surrenderApp.getPetAge());
    newPet.setVaccinationStatus(surrenderApp.getVaccinationStatus());
    newPet.setAdoptionStory("Please adopt me!");
    newPet.setAdoptionStatus("null");
    newPet.setPetType(petTypeRepo.findByTypeIgnoreCase(surrenderApp.getSurrenderPetType().getType()));
    return adoptablePetRepo.save(newPet);
  }

  public void processAdoptionApplication(AdoptionApplication adoptionApplication) {
    AdoptablePet pet = adoptablePetRepo.findById(adoptionApplication.getAdoptablePet().getId()).get();
    pet.setAdoptionStatus(adoptionApplication.getApplicationStatus());
    adoptablePetRepo.save(pet);
  }
}
