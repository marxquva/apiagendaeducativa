package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ms_persona_institucion")
public class PersonaInstitucionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona_institucion")
    private Integer idPersonaInstitucion;

    // Relación con persona
    @ManyToOne
    @JoinColumn(name = "id_persona_fk", referencedColumnName = "id_persona")
    private PersonaEntity persona;

    // Relación con institución
    @ManyToOne
    @JoinColumn(name = "id_institucion_fk", referencedColumnName = "id_institucion")
    private InstitucionEntity institucion;

    // Relación con rol académico
    @ManyToOne
    @JoinColumn(name = "id_rol_academico_fk", referencedColumnName = "id_rol_academico")
    private RolEntity rolAcademico;
}
