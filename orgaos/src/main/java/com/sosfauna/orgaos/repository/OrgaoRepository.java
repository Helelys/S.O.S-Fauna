package com.sosfauna.orgaos.repository;

import com.sosfauna.orgaos.model.entity.Orgao;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgaoRepository extends JpaRepository<Orgao, Integer> {
    Optional<Orgao> findByCnpj(String cnpj);
}
