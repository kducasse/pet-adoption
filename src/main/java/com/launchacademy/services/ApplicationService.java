package com.launchacademy.services;

public interface ApplicationService<T> {
  T processApplication(T application);
  T processApproval(T application);
  void deleteApplication(T application);
  void updateApplication(T application);
}
