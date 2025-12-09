package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ms_indicador")
public class IndicadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indicador")
    private Integer idIndicador;

    @Column(name = "nombre_indicador", nullable = false, length = 50)
    private String nombreIndicador;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne
    @JoinColumn(
            name = "id_institucion_fk",
            referencedColumnName = "id_institucion"
    )
    private InstitucionEntity institucionEntity;
}
