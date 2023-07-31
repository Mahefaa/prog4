package com.studies.prog4.repository;

import com.studies.prog4.repository.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  boolean existsByUsernameAndPassword(String username, String password);
}
