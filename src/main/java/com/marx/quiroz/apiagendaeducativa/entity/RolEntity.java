package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="sis_rol_academico")
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_academico")
    private Integer idRolAcademico;

    @Column(nullable = false, length = 25)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nivel")
    private Integer nivel = 1;

    @Column(name = "estado")
    private Integer estado = 1;
}
