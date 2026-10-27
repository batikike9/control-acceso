package com.condominio.acceso.repository;

import com.condominio.acceso.model.Residente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenteRepository extends JpaRepository<Residente, Long> {
    boolean existsByEmail(String email);
}
