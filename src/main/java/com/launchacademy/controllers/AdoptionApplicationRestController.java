package com.launchacademy.controllers;

import com.launchacademy.models.AdoptionApplication;
import com.launchacademy.repositories.AdoptionApplicationRepository;
import com.launchacademy.services.AdoptionApplicationService;
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
public class AdoptionApplicationRestController {

  private AdoptionApplicationRepository adoptionAppRepo;
  private AdoptionApplicationService adoptionApplicationService;

  @Autowired
  private AdoptionApplicationRestController(AdoptionApplicationRepository adoptionAppRepo,
      AdoptionApplicationService adoptionApplicationService) {
    this.adoptionAppRepo = adoptionAppRepo;
    this.adoptionApplicationService = adoptionApplicationService;
  }

  @GetMapping("/adoption_application")
  public Iterable<AdoptionApplication> getList() {
    return adoptionAppRepo.findAll();
  }

  @PostMapping("/adoption_application")
  public ResponseEntity create(@RequestBody @Valid AdoptionApplication adoptionApplication,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      return new ResponseEntity<>(adoptionApplicationService.processApplication(adoptionApplication), HttpStatus.CREATED);
    }
  }

  @PostMapping("/adoption_application_approval")
  public ResponseEntity getApproval(@RequestBody @Valid AdoptionApplication adoptionApplication,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      System.out.println("is it erroring?");
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      return new ResponseEntity<>(adoptionApplicationService.processsApproval(adoptionApplication),
          HttpStatus.CREATED);
    }
  }
}
