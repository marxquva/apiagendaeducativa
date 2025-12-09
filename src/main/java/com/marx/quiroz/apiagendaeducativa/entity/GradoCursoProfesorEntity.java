package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="ms_grado_curso_profesor")
public class GradoCursoProfesorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_curso_profesor")
    private Integer idCursoGrado;

    @Column(name = "nombre_asignacion", length = 50)
    private String nombreAsignacion;

    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;

    @Column(name = "estado")
    private Integer estado = 1;

    /* ===================== RELACIONES ====================== */

    // Relación con grado académico
    @ManyToOne
    @JoinColumn(
            name = "id_grado_academico_fk",
            referencedColumnName = "id_grado_academico"
    )
    private GradoAcademicoEntity gradoAcademico;

    // Relación con curso
    @ManyToOne
    @JoinColumn(
            name = "id_curso_fk",
            referencedColumnName = "id_curso"
    )
    private CursoEntity curso;

    // Relación con persona (profesor)
    @ManyToOne
    @JoinColumn(
            name = "id_persona_fk",
            referencedColumnName = "id_persona"
    )
    private PersonaEntity profesor;
}
