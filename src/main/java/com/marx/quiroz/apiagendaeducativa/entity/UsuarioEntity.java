package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="sis_usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password_hash", length = 150, nullable = false)
    private String passwordHash;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne
    @JoinColumn(
            name = "id_persona_fk",
            referencedColumnName = "id_persona"
    )
    private PersonaEntity persona;

}
