package com.launchacademy.controllers;

import com.launchacademy.models.AdoptablePet;
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
@RequestMapping("/api/v1/adoptable_pets")
public class AdoptablePetRestController {

  private AdoptablePetRepository adoptablePetRepository;
  private PetTypeRepository petTypeRepository;

  @Autowired
  private AdoptablePetRestController(AdoptablePetRepository adoptablePetRepository, PetTypeRepository petTypeRepository) {
    this.adoptablePetRepository = adoptablePetRepository;
    this.petTypeRepository = petTypeRepository;
  }

  @GetMapping
  public Iterable<AdoptablePet> getPets(@RequestParam(required = false) String type) {
    if (type == null) {
      return adoptablePetRepository.findAll();
    }else{
      int typeNumber = type.equals("Two-legged") ? 1 : 2;
      return petTypeRepository.findById(typeNumber).orElseThrow(AdoptablePetNotFoundException::new).getAdoptablePets();
    }
  }

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

  @GetMapping("/{id}")
  public AdoptablePet getOnePet(@PathVariable Integer id) {
    return adoptablePetRepository.findById(id)
        .orElseThrow(AdoptablePetNotFoundException::new);
  }
}
