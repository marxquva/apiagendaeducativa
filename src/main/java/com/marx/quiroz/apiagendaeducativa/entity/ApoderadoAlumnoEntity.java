package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ms_apoderado_alumno")
public class ApoderadoAlumnoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apoderado_alumno")
    private Integer idApoderadoAlumno;

    @Column(name = "parentesco", length = 50)
    private String parentesco;

    @Column(name = "es_principal")
    private Integer esPrincipal = 0;

    @ManyToOne
    @JoinColumn(name = "id_persona_alumno", referencedColumnName = "id_persona")
    private PersonaEntity alumno;

    @ManyToOne
    @JoinColumn(name = "id_persona_apoderado", referencedColumnName = "id_persona")
    private PersonaEntity apoderado;
}
