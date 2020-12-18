package com.iot.repository;

import com.iot.domain.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Integer> {
}
