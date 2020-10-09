package com.launchacademy.controllers;

import com.launchacademy.models.AdoptablePet;
import com.launchacademy.models.PetType;
import com.launchacademy.repositories.AdoptablePetRepository;
import com.launchacademy.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/adoptable-pets")
public class AdoptablePetRestController {

  private class AdoptablePetNotFoundException extends RuntimeException { };

  @ControllerAdvice
  private class AdoptablePetNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AdoptablePetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String adoptablePetNotFoundHandler(AdoptablePetNotFoundException exception) {
      return exception.getMessage();
    }
  }

  private AdoptablePetRepository adoptablePetRepo;
  private PetTypeRepository petTypeRepo;

  @Autowired
  private AdoptablePetRestController(AdoptablePetRepository adoptablePetRepo,
      PetTypeRepository petTypeRepo) {
    this.adoptablePetRepo = adoptablePetRepo;
    this.petTypeRepo = petTypeRepo;
  }

  @GetMapping
  public Iterable<AdoptablePet> getPets(@RequestParam(required = false) String type) {
    if (type == null) {
      return adoptablePetRepo.findAll();
    } else if (type.equals("all")) {
      return adoptablePetRepo.findAllByAdoptionStatus("approved");
    } else {
      return petTypeRepo.findById(type.equals("Two-legged") ? 1 : 2)
          .orElseThrow(AdoptablePetNotFoundException::new)
          .getAdoptablePets();
    }
  }

  @GetMapping("/{id}")
  public AdoptablePet getOnePet(@PathVariable Integer id, @RequestParam String species) {
    PetType petType = petTypeRepo.findByTypeIgnoreCase(species);
    return adoptablePetRepo.findByIdAndPetType(id, petType)
        .orElseThrow(AdoptablePetNotFoundException::new);
  }
}
