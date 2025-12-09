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
@Table(name="ms_mensaje")
public class MensajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Integer idMensaje;

    @Column(name = "asunto", length = 120)
    private String asunto;

    @Column(name = "contenido", columnDefinition = "text")
    private String contenido;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "estado")
    private Integer estado = 1;

    /* ===================== RELACIONES ====================== */

    // Usuario remitente
    @ManyToOne
    @JoinColumn(name = "id_usuario_remitente", referencedColumnName = "id_usuario")
    private UsuarioEntity usuarioRemitente;

    // Usuario destinatario
    @ManyToOne
    @JoinColumn(name = "id_usuario_destinatario", referencedColumnName = "id_usuario")
    private UsuarioEntity usuarioDestinatario;

    // Tipo de mensaje
    @ManyToOne
    @JoinColumn(name = "id_tipo_mensaje_fk", referencedColumnName = "id_tipo_mensaje")
    private TipoMensajeEntity tipoMensaje;

    // Institución
    @ManyToOne
    @JoinColumn(name = "id_institucion_fk", referencedColumnName = "id_institucion")
    private InstitucionEntity institucion;

    /* ===================== RELACIÓN CON INDICADORES ====================== */
    @OneToMany(mappedBy = "mensaje")
    private List<IndicadorMensajeEntity> indicadores = new ArrayList<>();
}
