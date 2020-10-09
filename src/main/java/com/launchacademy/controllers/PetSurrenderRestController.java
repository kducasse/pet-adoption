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
  private PetSurrenderApplicationService petSurrenderAppService;

  @Autowired
  private PetSurrenderRestController(PetSurrenderApplicationRepository petSurrenderAppRepo,
      PetSurrenderApplicationService petSurrenderAppService) {
    this.petSurrenderAppRepo = petSurrenderAppRepo;
    this.petSurrenderAppService = petSurrenderAppService;
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
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      return new ResponseEntity<>(petSurrenderAppService.processApp(petSurrenderApplication),
          HttpStatus.CREATED);
    }
  }

  @PostMapping("/approval")
  public ResponseEntity getApproval(
      @RequestBody @Valid PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      return new ResponseEntity<>(petSurrenderAppService.processApproval(petSurrenderApplication),
          HttpStatus.OK);
    }
  }

  @PutMapping("/update")
  private ResponseEntity update(@RequestParam Integer id,
      @RequestBody @Valid PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      petSurrenderAppService.updateApplication(petSurrenderApplication);
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping("/delete")
  private ResponseEntity<List> delete(@RequestParam Integer id,
      @RequestBody @Valid PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    } else {
      petSurrenderAppService.deleteApplication(petSurrenderApplication);
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }
}
