package com.launchacademy.services;

import com.launchacademy.models.PetSurrenderApplication;
import com.launchacademy.models.PetType;
import com.launchacademy.repositories.PetSurrenderApplicationRepository;
import com.launchacademy.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetSurrenderApplicationService {
  private PetTypeRepository petTypeRepository;
  private PetSurrenderApplicationRepository petSurrenderAppRepo;
  private AdoptablePetService adoptablePetService;

  @Autowired
  private PetSurrenderApplicationService(PetTypeRepository petTypeRepository,
      PetSurrenderApplicationRepository petSurrenderAppRepo,
      AdoptablePetService adoptablePetService) {
    this.petTypeRepository = petTypeRepository;
    this.petSurrenderAppRepo = petSurrenderAppRepo;
    this.adoptablePetService = adoptablePetService;
  }

  public PetSurrenderApplication processApp(PetSurrenderApplication petApp) {
    int petTypeId = petApp.getPetType().equals("Two-legged") ? 1 : 2;
    PetType petType = petTypeRepository.findById(petTypeId).get();
    petApp.setPetType(petType);
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
    PetType petType = petTypeRepository.findByTypeIgnoreCase(petSurrenderApplication.getPetType().getType());
    application.setName(petSurrenderApplication.getName());
    application.setPhoneNumber(petSurrenderApplication.getPhoneNumber());
    application.setEmail(petSurrenderApplication.getEmail());
    application.setPetName(petSurrenderApplication.getPetName());
    application.setPetAge(petSurrenderApplication.getPetAge());
    application.setPetType(petType);
    application.setImgUrl(petSurrenderApplication.getImgUrl());
    application.setVaccinationStatus(petSurrenderApplication.getVaccinationStatus());
    petSurrenderAppRepo.save(application);
  }
}
