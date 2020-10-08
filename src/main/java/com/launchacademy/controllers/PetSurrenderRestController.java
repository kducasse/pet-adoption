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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PetSurrenderRestController {

  private PetSurrenderApplicationRepository petSurrenderAppRepo;
  private PetSurrenderApplicationService petSurrenderAppService;

  @Autowired
  private PetSurrenderRestController(PetSurrenderApplicationRepository petSurrenderAppRepo,
      PetSurrenderApplicationService petSurrenderAppService) {
    this.petSurrenderAppRepo = petSurrenderAppRepo;
    this.petSurrenderAppService = petSurrenderAppService;
  }

  @GetMapping("/surrender_application")
  public Iterable<PetSurrenderApplication> getList() {
    return petSurrenderAppRepo.findAll();
  }

  @PostMapping("/pet_surrender_applications")
  public ResponseEntity create(@RequestBody @Valid PetSurrenderApplication petSurrenderApplication, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      return new ResponseEntity<>(petSurrenderAppService.processApp(petSurrenderApplication),
          HttpStatus.CREATED);
    }
  }

  @PostMapping("/adoption_surrender_approval")
  public ResponseEntity getApproval(@RequestBody @Valid PetSurrenderApplication petSurrenderApplication, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    }else {
      return new ResponseEntity<>(petSurrenderAppService.processsApproval(petSurrenderApplication),
          HttpStatus.CREATED);
    }
  }
}
