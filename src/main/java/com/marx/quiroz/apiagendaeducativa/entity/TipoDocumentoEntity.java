package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="sis_tipo_documento")
public class TipoDocumentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name = "nombre_tipo_documento", nullable = false, length = 50)
    private String nombreTipoDocumento;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "color_etiqueta", length = 7)
    private String colorEtiqueta;

    @Column(name = "utilidad")
    private Integer utilidad = 2;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "expresion_regular", length = 15)
    private String expresionRegular;

    @Column(name = "estado")
    private Integer estado = 1;
}

