package com.studies.prog4.service;

import com.studies.prog4.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository repository;

  public boolean existsByUsernameAndPassword(String username, String password) {
    return repository.existsByUsernameAndPassword(username, password);
  }
}