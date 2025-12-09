package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ms_adjunto_mensaje")
public class AdjuntoMensajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adjunto_mensaje")
    private Integer idAdjuntoMensaje;

    @Column(name = "archivo_url", length = 120)
    private String archivoUrl;

    // Mensaje relacionado
    @ManyToOne
    @JoinColumn(
            name = "id_mensaje_fk",
            referencedColumnName = "id_mensaje"
    )
    private MensajeEntity mensaje;
}
