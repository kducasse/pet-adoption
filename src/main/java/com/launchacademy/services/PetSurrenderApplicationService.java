package com.launchacademy.services;

import com.launchacademy.models.PetSurrenderApplication;
import com.launchacademy.models.PetType;
import com.launchacademy.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetSurrenderApplicationService {
  private PetTypeRepository petTypeRepository;

  @Autowired
  private PetSurrenderApplicationService(PetTypeRepository petTypeRepository) {
    this.petTypeRepository = petTypeRepository;
  }

  public PetSurrenderApplication processApp(PetSurrenderApplication petApp) {
    int petTypeId = petApp.getPetType().equals("Two-legged") ? 1 : 2;
    PetType petType = petTypeRepository.findById(petTypeId).get();
    petApp.setPetType(petType);
    return petApp;
  }
}
