package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="ms_persona_institucion")
public class PersonaInstitucionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona_institucion")
    private Integer idPersonaInstitucion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne
    @JoinColumn(name = "id_persona_fk")
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "id_institucion_fk")
    private InstitucionEntity institucion;

    @ManyToOne
    @JoinColumn(name = "id_rol_academico_fk")
    private RolEntity rolAcademico;
}
