package com.security.demo.repository;

import com.security.demo.model.entity.ResetSenhaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetSenhaRepositoryUser extends JpaRepository<ResetSenhaUser, String> {
    Optional<ResetSenhaUser> findByCodigoUser(String codigo);
}
