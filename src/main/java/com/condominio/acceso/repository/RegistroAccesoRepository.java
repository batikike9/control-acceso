package com.condominio.acceso.repository;

import com.condominio.acceso.model.RegistroAcceso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroAccesoRepository extends JpaRepository<RegistroAcceso, Long> {
    List<RegistroAcceso> findByFechaHoraBetweenOrderByFechaHoraDesc(LocalDateTime desde, LocalDateTime hasta);
}
