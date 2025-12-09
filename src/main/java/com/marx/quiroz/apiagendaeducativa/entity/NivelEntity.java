package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ms_nivel_academico")
public class NivelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_academico")
    private Integer idNivelEducativo;

    @Column(name = "nombre_nivel_academico", nullable = false, length = 50)
    private String nombreIndicador;

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
