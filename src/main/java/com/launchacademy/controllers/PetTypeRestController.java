package com.launchacademy.controllers;

import com.launchacademy.models.PetType;
import com.launchacademy.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pet-types")
public class PetTypeRestController {

  private PetTypeRepository petTypeRepository;

  @Autowired
  private PetTypeRestController(PetTypeRepository petTypeRepository) {
    this.petTypeRepository = petTypeRepository;
  }

  @GetMapping
  public Iterable<PetType> getPetTypes() {
    return petTypeRepository.findAll();
  }
}
