package com.launchacademy.services;

import com.launchacademy.models.AdoptablePet;
import com.launchacademy.models.AdoptionApplication;
import com.launchacademy.repositories.AdoptablePetRepository;
import com.launchacademy.repositories.AdoptionApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AdoptionApplicationService implements ApplicationService<AdoptionApplication> {

  private AdoptionApplicationRepository adoptionApplicationRepo;
  private AdoptablePetRepository adoptablePetRepo;
  private AdoptablePetService adoptablePetService;

  @Autowired
  private AdoptionApplicationService(AdoptionApplicationRepository adoptionApplicationRepo,
      AdoptablePetService adoptablePetService, AdoptablePetRepository adoptablePetRepo) {
    this.adoptionApplicationRepo = adoptionApplicationRepo;
    this.adoptablePetService = adoptablePetService;
    this.adoptablePetRepo = adoptablePetRepo;
  }

  public AdoptionApplication processApplication(AdoptionApplication adoptionApplication) {
    AdoptablePet pet = adoptablePetRepo
        .findById(adoptionApplication.getAdoptablePet().getId()).get();
    adoptionApplication.setAdoptablePet(pet);
    return adoptionApplicationRepo.save(adoptionApplication);
  }

  public AdoptionApplication processApproval(AdoptionApplication adoptionApplication) {
    AdoptionApplication application = adoptionApplicationRepo.findById(adoptionApplication.getId()).get();
    application.setApplicationStatus(adoptionApplication.getApplicationStatus());
    adoptionApplicationRepo.save(application);
    adoptablePetService.processAdoptionApplication(adoptionApplication);
    return adoptionApplicationRepo.findById(adoptionApplication.getId()).get();
  }

  public void deleteApplication(AdoptionApplication adoptionApplication) {
    AdoptionApplication application = adoptionApplicationRepo.findById(adoptionApplication.getId()).get();
    adoptionApplicationRepo.delete(application);
  }

  public void updateApplication(AdoptionApplication adoptionApplication) {
    AdoptionApplication application = adoptionApplicationRepo.findById(adoptionApplication.getId()).get();
    application.setName(adoptionApplication.getName());
    application.setPhoneNumber(adoptionApplication.getPhoneNumber());
    application.setEmail(adoptionApplication.getEmail());
    application.setHomeStatus(adoptionApplication.getHomeStatus());
    adoptionApplicationRepo.save(application);
  }
}
