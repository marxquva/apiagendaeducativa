package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ms_indicador_mensaje")
public class IndicadorMensajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indicador_mensaje")
    private Integer idIndicadorMensaje;

    /* ===================== RELACIONES ====================== */

    // Mensaje
    @ManyToOne
    @JoinColumn(
            name = "id_mensaje_fk",
            referencedColumnName = "id_mensaje"
    )
    private MensajeEntity mensaje;

    // Indicador
    @ManyToOne
    @JoinColumn(
            name = "id_indicador_fk",
            referencedColumnName = "id_indicador"
    )
    private IndicadorEntity indicador;
}
