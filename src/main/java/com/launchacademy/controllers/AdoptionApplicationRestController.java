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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/adoption-applications")
public class AdoptionApplicationRestController {

  private AdoptionApplicationRepository adoptionAppRepo;
  private AdoptionApplicationService adoptionApplicationService;

  @Autowired
  private AdoptionApplicationRestController(AdoptionApplicationRepository adoptionAppRepo,
      AdoptionApplicationService adoptionApplicationService) {
    this.adoptionAppRepo = adoptionAppRepo;
    this.adoptionApplicationService = adoptionApplicationService;
  }

  @GetMapping()
  public Iterable<AdoptionApplication> getList() {
    return adoptionAppRepo.findAll();
  }

  @GetMapping("/pending")
  public Iterable<AdoptionApplication> getPendingList() {
    return adoptionAppRepo.findAllByApplicationStatus("pending");
  }

  @PostMapping()
  public ResponseEntity create(@RequestBody @Valid AdoptionApplication adoptionApplication,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      return new ResponseEntity<>(
          adoptionApplicationService.processApplication(adoptionApplication), HttpStatus.CREATED);
    }
  }

  @PostMapping("/approval")
  public ResponseEntity getApproval(@RequestBody @Valid AdoptionApplication adoptionApplication,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      return new ResponseEntity<>(adoptionApplicationService.processApproval(adoptionApplication),
          HttpStatus.OK);
    }
  }

  @PutMapping("/update")
  private ResponseEntity update(@RequestParam Integer id,
      @RequestBody @Valid AdoptionApplication adoptionApplication, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      adoptionApplicationService.updateApplication(adoptionApplication);
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping("/delete")
  private ResponseEntity delete(@RequestParam Integer id,
      @RequestBody @Valid AdoptionApplication adoptionApplication, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      adoptionApplicationService.deleteApplication(adoptionApplication);
      return new ResponseEntity(HttpStatus.OK);
    }
  }
}
