package com.launchacademy.controllers;

import com.launchacademy.models.AdoptionApplication;
import com.launchacademy.repositories.AdoptionApplicationRepository;
import com.launchacademy.services.AdoptionApplicationService;
import com.launchacademy.services.PetResponseEntityService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
  private PetResponseEntityService<AdoptionApplication, AdoptionApplicationService> petResponseEntity;

  @Autowired
  private AdoptionApplicationRestController(AdoptionApplicationRepository adoptionAppRepo,
      AdoptionApplicationService adoptionApplicationService) {
    this.adoptionAppRepo = adoptionAppRepo;
    this.petResponseEntity = new PetResponseEntityService<>(adoptionApplicationService);
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
   return petResponseEntity.create(adoptionApplication, bindingResult);
  }

  @PostMapping("/approval")
  public ResponseEntity getApproval(@RequestBody @Valid AdoptionApplication adoptionApplication,
      BindingResult bindingResult) {
    return petResponseEntity.getApproval(adoptionApplication, bindingResult);
  }

  @PutMapping("/update")
  private ResponseEntity update(@RequestParam Integer id,
      @RequestBody @Valid AdoptionApplication adoptionApplication, BindingResult bindingResult) {
    return petResponseEntity.update(adoptionApplication, bindingResult);
  }

  @DeleteMapping("/delete")
  private ResponseEntity delete(@RequestParam Integer id,
      @RequestBody @Valid AdoptionApplication adoptionApplication, BindingResult bindingResult) {
    return petResponseEntity.delete(adoptionApplication, bindingResult);
  }
}
