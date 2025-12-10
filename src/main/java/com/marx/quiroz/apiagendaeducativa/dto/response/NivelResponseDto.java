package com.marx.quiroz.apiagendaeducativa.dto.response;

import com.marx.quiroz.apiagendaeducativa.entity.NivelEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TurnoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NivelResponseDto {
    private Integer idNivelAcademico;
    private String nombreNivelAcademico;
    private String descripcion;
    private Integer estado;
    private Integer idInstitucion;
    private String nombreInstitucion;

    public NivelResponseDto(NivelEntity nivel) {
        this.idNivelAcademico  = nivel.getIdNivelAcademico();
        this.nombreNivelAcademico  = nivel.getNombreNivelAcademico();
        this.descripcion  = nivel.getDescripcion();
        this.estado = nivel.getEstado();

        if (nivel.getInstitucionEntity() != null) {
            this.idInstitucion = nivel.getInstitucionEntity().getIdInstitucion();
            this.nombreInstitucion = nivel.getInstitucionEntity().getNombreInstitucion();
        }
    }
}
