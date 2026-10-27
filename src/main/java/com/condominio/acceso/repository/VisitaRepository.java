package com.condominio.acceso.repository;

import com.condominio.acceso.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
    Optional<Visita> findByCodigoAcceso(String codigoAcceso);
    List<Visita> findByAnfitrionId(Long residenteId);
}
