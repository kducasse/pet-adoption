package com.launchacademy.services;

import com.launchacademy.models.PetSurrenderApplication;
import com.launchacademy.models.PetType;
import com.launchacademy.repositories.PetSurrenderApplicationRepository;
import com.launchacademy.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetSurrenderApplicationService implements ApplicationService<PetSurrenderApplication> {
  private PetSurrenderApplicationRepository petSurrenderAppRepo;
  private PetTypeRepository petTypeRepo;
  private AdoptablePetService adoptablePetService;

  @Autowired
  private PetSurrenderApplicationService(PetTypeRepository petTypeRepo,
      PetSurrenderApplicationRepository petSurrenderAppRepo,
      AdoptablePetService adoptablePetService) {
    this.petTypeRepo = petTypeRepo;
    this.petSurrenderAppRepo = petSurrenderAppRepo;
    this.adoptablePetService = adoptablePetService;
  }

  public PetSurrenderApplication processApplication(PetSurrenderApplication petApp) {
    int petTypeId = petApp.getSurrenderPetType().getType().equals("Two-legged") ? 1 : 2;
    PetType petType = petTypeRepo.findById(petTypeId).get();
    petApp.setSurrenderPetType(petType);
    return petSurrenderAppRepo.save(petApp);
  }

  public PetSurrenderApplication processApproval(PetSurrenderApplication petSurrenderApplication) {
    PetSurrenderApplication surrenderApp = petSurrenderAppRepo.findById(petSurrenderApplication.getId()).get();
    surrenderApp.setApplicationStatus(petSurrenderApplication.getApplicationStatus());
    petSurrenderAppRepo.save(surrenderApp);
    if(petSurrenderApplication.getApplicationStatus().equals("approved")) {
      adoptablePetService.processNewPet(surrenderApp);
    }
    return petSurrenderAppRepo.findById(petSurrenderApplication.getId()).get();
  }

  public void deleteApplication(PetSurrenderApplication petSurrenderApplication) {
    PetSurrenderApplication application = petSurrenderAppRepo.findById(petSurrenderApplication.getId()).get();
    petSurrenderAppRepo.delete(application);
  }

  public void updateApplication(PetSurrenderApplication petSurrenderApplication) {
    PetSurrenderApplication application = petSurrenderAppRepo.findById(petSurrenderApplication.getId()).get();
    PetType petType = petTypeRepo.findByTypeIgnoreCase(petSurrenderApplication.getSurrenderPetType().getType());
    application.setName(petSurrenderApplication.getName());
    application.setPhoneNumber(petSurrenderApplication.getPhoneNumber());
    application.setEmail(petSurrenderApplication.getEmail());
    application.setPetName(petSurrenderApplication.getPetName());
    application.setPetAge(petSurrenderApplication.getPetAge());
    application.setSurrenderPetType(petType);
    application.setImgUrl(petSurrenderApplication.getImgUrl());
    application.setVaccinationStatus(petSurrenderApplication.getVaccinationStatus());
    petSurrenderAppRepo.save(application);
  }
}
