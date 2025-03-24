package com.sos_fauna.denuncias.repository;

import com.sos_fauna.denuncias.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {
}
