package com.marx.quiroz.apiagendaeducativa.dto.response;

import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TurnoEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TurnoResponseDto {
    private Integer idTurno;
    private String nombreTurno;
    private String descripcion;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFinal;
    private Integer estado;
    private Integer idInstitucion;
    private String nombreInstitucion;

    public TurnoResponseDto(TurnoEntity turno) {
        this.idTurno  = turno.getIdTurno();
        this.nombreTurno  = turno.getNombreTurno();
        this.descripcion  = turno.getDescripcion();
        this.horaInicio = turno.getHoraInicio();
        this.horaFinal = turno.getHoraFinal();
        this.estado = turno.getEstado();

        if (turno.getInstitucionEntity() != null) {
            this.idInstitucion = turno.getInstitucionEntity().getIdInstitucion();
            this.nombreInstitucion = turno.getInstitucionEntity().getNombreInstitucion();
        }
    }

}
