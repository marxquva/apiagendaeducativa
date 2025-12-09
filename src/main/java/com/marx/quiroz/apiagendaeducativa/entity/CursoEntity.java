package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="ms_curso")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "nombre_curso", nullable = false, length = 50)
    private String nombreCurso;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne
    @JoinColumn(
            name = "id_institucion_fk",
            referencedColumnName = "id_institucion"
    )
    private InstitucionEntity institucionEntity;
}
