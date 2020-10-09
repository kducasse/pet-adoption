package com.launchacademy.services;

import com.launchacademy.models.AdoptablePet;
import com.launchacademy.models.AdoptionApplication;
import com.launchacademy.repositories.AdoptablePetRepository;
import com.launchacademy.repositories.AdoptionApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptionApplicationService {

  private AdoptionApplicationRepository adoptionApplicationRepository;
  private AdoptablePetService adoptablePetService;
  private AdoptablePetRepository adoptablePetRepository;

  @Autowired
  private AdoptionApplicationService(AdoptionApplicationRepository adoptionApplicationRepository,
      AdoptablePetService adoptablePetService, AdoptablePetRepository adoptablePetRepository) {
    this.adoptionApplicationRepository = adoptionApplicationRepository;
    this.adoptablePetService = adoptablePetService;
    this.adoptablePetRepository = adoptablePetRepository;
  }

  public AdoptionApplication processApproval(AdoptionApplication adoptionApplication) {
    AdoptionApplication application = adoptionApplicationRepository.findById(adoptionApplication.getId()).get();
    application.setApplicationStatus(adoptionApplication.getApplicationStatus());
    adoptionApplicationRepository.save(application);
    adoptablePetService.processAdoptionApplication(adoptionApplication);
    return adoptionApplicationRepository.findById(adoptionApplication.getId()).get();
  }

  public AdoptionApplication processApplication(AdoptionApplication adoptionApplication) {
    AdoptablePet pet = adoptablePetRepository
        .findById(adoptionApplication.getAdoptablePet().getId()).get();
    adoptionApplication.setAdoptablePet(pet);
    return adoptionApplicationRepository.save(adoptionApplication);
  }

  public void deleteApplication(AdoptionApplication adoptionApplication) {
    AdoptionApplication application = adoptionApplicationRepository.findById(adoptionApplication.getId()).get();
    adoptionApplicationRepository.delete(application);
  }

  public void updateApplication(AdoptionApplication adoptionApplication) {
    AdoptionApplication application = adoptionApplicationRepository.findById(adoptionApplication.getId()).get();
    application.setName(adoptionApplication.getName());
    application.setPhoneNumber(adoptionApplication.getPhoneNumber());
    application.setEmail(adoptionApplication.getEmail());
    application.setHomeStatus(adoptionApplication.getHomeStatus());
    adoptionApplicationRepository.save(application);
  }


}
