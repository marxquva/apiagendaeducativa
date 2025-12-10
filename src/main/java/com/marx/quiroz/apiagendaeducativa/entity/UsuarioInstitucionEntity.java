package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="ms_usuario_institucion")
public class UsuarioInstitucionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_institucion")
    private Integer idUsuarioInstitucion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_institucion_fk")
    private InstitucionEntity institucion;

    @ManyToOne
    @JoinColumn(name = "id_perfil_sistema_fk")
    private PerfilEntity perfilSistema;

}
