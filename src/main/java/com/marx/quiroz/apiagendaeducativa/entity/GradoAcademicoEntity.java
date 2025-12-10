package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ms_grado_academico",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_grado_combinacion",
                columnNames = {
                        "nombre_grado",
                        "id_anio_academico_fk",
                        "id_institucion_fk",
                        "id_nivel_academico_fk",
                        "id_turno_fk"
                }
        )
)
public class GradoAcademicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_academico")
    private Integer idGradoAcademico;

    @Column(name = "nombre_grado", length = 50, nullable = false)
    private String nombreGrado;

    @Column(name = "descripcion_aula", columnDefinition = "text")
    private String descripcionAula;

    @Column(name = "numero_vacante")
    private Integer numeroVacante;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "estado")
    private Integer estado = 1;

    /* ===================== RELACIÓNES ====================== */

    // Año académico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anio_academico_fk", referencedColumnName = "id_anio_academico")
    private PeriodoEntity anioAcademico;

    // Institución
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_institucion_fk", referencedColumnName = "id_institucion")
    private InstitucionEntity institucion;

    // Nivel académico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_academico_fk", referencedColumnName = "id_nivel_academico")
    private NivelEntity nivelAcademico;

    // Turno
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turno_fk", referencedColumnName = "id_turno")
    private TurnoEntity turno;

    /* ===================== RELACIÓN CON CURSOS ====================== */
    @OneToMany(mappedBy = "gradoAcademico")
    private List<CursoGradoEntity> cursos = new ArrayList<>();

    /* ===================== RELACIÓN CON ALUMNOS MATRICULADOS ====================== */
    @OneToMany(mappedBy = "gradoAcademico")
    private List<MatriculaAlumnoEntity> alumnosMatriculados = new ArrayList<>();
}
