package com.security.demo.repository;

import com.security.demo.model.entity.ResetSenhaOng;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetSenhaRepositoryOng extends JpaRepository<ResetSenhaOng, String> {
    Optional<ResetSenhaOng> findByCodigoOng(String codigo);
}
