package com.kcj_customer_be.security.repo;

import com.kcj_customer_be.security.entity.DeactivatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeactivatedTokenRepository extends JpaRepository<DeactivatedToken, UUID> {
}
