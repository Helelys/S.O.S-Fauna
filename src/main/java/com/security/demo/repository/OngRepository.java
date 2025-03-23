package com.security.demo.repository;

import com.security.demo.model.entity.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long> {
    Optional<Ong> findByName(String name);
}