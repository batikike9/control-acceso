package com.condominio.acceso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Unidad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String torre;
    @Column(nullable = false)
    private String numero;
    private boolean activa = true;
}
