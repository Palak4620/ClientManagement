package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmailId(String emailId);
    List<Client> findByIsActiveTrue();
    List<Client> findByEmailIdContainingIgnoreCaseOrMobileNumberContaining(String email, String mobile);
}