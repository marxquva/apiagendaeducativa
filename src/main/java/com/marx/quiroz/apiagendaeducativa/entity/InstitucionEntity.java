package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="sis_institucion")
public class InstitucionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Integer idInstitucion;

    @Column(name = "nombre_institucion", nullable = false, length = 120)
    private String nombreInstitucion;

    @Column(name = "codigo", columnDefinition = "UUID")
    private UUID codigo = UUID.randomUUID();

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "imagen", length = 120)
    private String imagen;

    @Column(name = "direccion", length = 120)
    private String direccion;

    @Column(name = "dominio", length = 120)
    private String dominio;

    @Column(name = "nota_minima", length = 3)
    private String notaMinima;

    @Column(name = "nota_maxima", length = 3)
    private String notaMaxima;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne
    @JoinColumn(
            name = "id_tipo_institucion_fk",
            referencedColumnName = "id_tipo_institucion",
            nullable = false
    )
    private TipoInstitucionEntity tipoInstitucion;

}
