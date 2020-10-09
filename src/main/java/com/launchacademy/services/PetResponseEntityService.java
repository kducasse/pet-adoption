package com.launchacademy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class PetResponseEntityService<U, T extends ApplicationService<U>> {

  private final T service;

  @Autowired
  public PetResponseEntityService(T service) {
    this.service = service;
    }

  public ResponseEntity create(U application, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    }else {
      return new ResponseEntity<>(service.processApplication(application), HttpStatus.CREATED);
    }
  }

  public ResponseEntity getApproval(U application, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    }else {
      return new ResponseEntity<>(service.processApproval(application), HttpStatus.OK);
    }
  }

  public ResponseEntity update(U application, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    }else {
      service.updateApplication(application);
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  public ResponseEntity delete(U application, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    }else {
      service.deleteApplication(application);
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }
}
