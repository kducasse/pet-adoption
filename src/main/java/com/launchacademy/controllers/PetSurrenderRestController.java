package com.launchacademy.controllers;

import com.launchacademy.models.PetSurrenderApplication;
import com.launchacademy.repositories.PetSurrenderApplicationRepository;
import com.launchacademy.services.PetSurrenderApplicationService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pet_surrender_applications")
public class PetSurrenderRestController {

  private PetSurrenderApplicationRepository petSurrenderAppRepo;
  private PetSurrenderApplicationService petSurrenderAppService;

  @Autowired
  private PetSurrenderRestController(PetSurrenderApplicationRepository petSurrenderAppRepo,
      PetSurrenderApplicationService petSurrenderAppService) {
    this.petSurrenderAppRepo = petSurrenderAppRepo;
    this.petSurrenderAppService = petSurrenderAppService;
  }

  @PostMapping
  private ResponseEntity create(@RequestBody @Valid PetSurrenderApplication petSurrenderApplication, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      return new ResponseEntity<>(petSurrenderAppRepo.save(petSurrenderAppService.processApp(petSurrenderApplication)),
          HttpStatus.CREATED);
    }
  }
}
