package com.condominio.acceso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Residente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    private String apellidos;
    @Column(nullable = false, unique = true)
    private String email;
    private String telefono;
    @ManyToOne(optional = false)
    private Unidad unidad;
    private boolean activo = true;
}
