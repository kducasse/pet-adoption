package com.launchacademy.controllers;

import com.launchacademy.models.PetSurrenderApplication;
import com.launchacademy.repositories.PetSurrenderApplicationRepository;
import com.launchacademy.services.PetResponseEntityService;
import com.launchacademy.services.PetSurrenderApplicationService;
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
@RequestMapping("/api/v1/pet-surrender-applications")
public class PetSurrenderRestController {

  private PetSurrenderApplicationRepository petSurrenderAppRepo;
  private PetResponseEntityService<PetSurrenderApplication, PetSurrenderApplicationService> petResponseEntityService;

  @Autowired
  private PetSurrenderRestController(PetSurrenderApplicationRepository petSurrenderAppRepo,
      PetSurrenderApplicationService petSurrenderAppService) {
    this.petSurrenderAppRepo = petSurrenderAppRepo;
    this.petResponseEntityService = new PetResponseEntityService<>(petSurrenderAppService);
  }

  @GetMapping()
  public Iterable<PetSurrenderApplication> getList() {
    return petSurrenderAppRepo.findAll();
  }

  @GetMapping("/pending")
  public Iterable<PetSurrenderApplication> getPendingList() {
    return petSurrenderAppRepo.findAllByApplicationStatus("pending");
  }

  @PostMapping()
  public ResponseEntity create(@RequestBody @Valid PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {
    return petResponseEntityService.create(petSurrenderApplication, bindingResult);
  }

  @PostMapping("/approval")
  public ResponseEntity getApproval(
      @RequestBody @Valid PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {
    return petResponseEntityService.getApproval(petSurrenderApplication, bindingResult);
  }

  @PutMapping("/update")
  private ResponseEntity update(@RequestParam Integer id,
      @RequestBody @Valid PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {
    return petResponseEntityService.update(petSurrenderApplication, bindingResult);
  }

  @DeleteMapping("/delete")
  private ResponseEntity delete(@RequestParam Integer id,
      @RequestBody @Valid PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {
   return petResponseEntityService.delete(petSurrenderApplication, bindingResult);
  }
}
