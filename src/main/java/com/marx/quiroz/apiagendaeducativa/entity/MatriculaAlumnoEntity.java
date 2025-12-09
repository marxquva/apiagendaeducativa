package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="ms_matricula_alumno")
public class MatriculaAlumnoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula_alumno")
    private Integer idMatriculaAlumno;

    @Column(name = "codigo")
    private UUID codigo;

    @Column(name = "fecha_matricula")
    private LocalDateTime fechaMatricula;

    @Column(name = "estado")
    private Integer estado = 1;


    /* ===================== RELACIONES ====================== */

    // Relación con persona (alumno)
    @ManyToOne
    @JoinColumn(
            name = "id_persona_fk",
            referencedColumnName = "id_persona"
    )
    private PersonaEntity persona;

    // Relación con grado académico
    @ManyToOne
    @JoinColumn(
            name = "id_grado_academico_fk",
            referencedColumnName = "id_grado_academico"
    )
    private GradoAcademicoEntity gradoAcademico;
}
