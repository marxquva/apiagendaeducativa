package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ms_curso_grado")
public class CursoGradoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso_grado")
    private Integer idCursoGrado;

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
}
