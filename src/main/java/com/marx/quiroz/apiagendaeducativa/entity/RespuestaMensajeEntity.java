package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ms_respuesta_mensaje")
public class RespuestaMensajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta_mensaje")
    private Integer idRespuestaMensaje;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "indicador_respuesta", length = 50)
    private String indicadorRespuesta;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_mensaje_fk",
            referencedColumnName = "id_mensaje"
    )
    private MensajeEntity mensaje;

}
