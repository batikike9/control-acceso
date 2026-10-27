package com.condominio.acceso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class Visita {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombreVisitante;
    private String motivo;
    @ManyToOne(optional = false)
    private Residente anfitrion;
    @Column(nullable = false, unique = true)
    private String codigoAcceso;
    private LocalDateTime vigenteDesde;
    private LocalDateTime vigenteHasta;
    @Enumerated(EnumType.STRING)
    private EstadoVisita estado = EstadoVisita.PENDIENTE;
}
