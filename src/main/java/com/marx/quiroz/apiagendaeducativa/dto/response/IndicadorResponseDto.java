package com.marx.quiroz.apiagendaeducativa.dto.response;

import com.marx.quiroz.apiagendaeducativa.entity.CursoEntity;
import com.marx.quiroz.apiagendaeducativa.entity.IndicadorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IndicadorResponseDto {

    private Integer idIndicador;
    private String nombreIndicador;
    private String descripcion;
    private Integer tipo;
    private Integer estado;
    private Integer idInstitucion;
    private String nombreInstitucion;

    public IndicadorResponseDto(IndicadorEntity indicador) {
        this.idIndicador  = indicador.getIdIndicador();
        this.nombreIndicador  = indicador.getNombreIndicador();
        this.descripcion  = indicador.getDescripcion();
        this.tipo = indicador.getTipo();
        this.estado = indicador.getEstado();

        if (indicador.getInstitucionEntity() != null) {
            this.idInstitucion = indicador.getInstitucionEntity().getIdInstitucion();
            this.nombreInstitucion = indicador.getInstitucionEntity().getNombreInstitucion();
        }
    }
}
