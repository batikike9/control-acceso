package com.condominio.acceso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class RegistroAcceso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Visita visita; // null si el código no existe
    private String codigoIntentado;
    @Enumerated(EnumType.STRING)
    private TipoAcceso tipo;
    @Enumerated(EnumType.STRING)
    private ResultadoAcceso resultado;
    private String motivoDenegacion;
    private LocalDateTime fechaHora = LocalDateTime.now();
}
