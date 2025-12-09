package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="ms_turno")
public class TurnoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Integer idTurno;

    @Column(name = "nombre_turno", nullable = false, length = 50)
    private String nombreTurno;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "hora_inicio")
    private LocalDateTime horaInicio = LocalDateTime.now();

    @Column(name = "hora_final")
    private LocalDateTime horaFinal;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne
    @JoinColumn(
            name = "id_institucion_fk",
            referencedColumnName = "id_institucion"
    )
    private InstitucionEntity institucionEntity;
}
