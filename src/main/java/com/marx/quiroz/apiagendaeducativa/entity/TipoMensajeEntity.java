package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="sis_tipo_mensaje")
public class TipoMensajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_mensaje")
    private Integer idTipoMensaje;

    @Column(name = "nombre_tipo_mensaje", nullable = false, length = 50)
    private String nombreTipoMensaje;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "color_rgb", length = 7)
    private String colorRgb;

    @Column(name = "requiere_respuesta")
    private Integer requiereRespuesta = 0;

    @Column(name = "estado")
    private Integer estado = 1;
}
