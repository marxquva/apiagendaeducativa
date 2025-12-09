package com.marx.quiroz.apiagendaeducativa.dto.response;

import com.marx.quiroz.apiagendaeducativa.entity.TipoInstitucionEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoInstitucionResponseDto {
    private Integer idTipoInstitucion;
    private String nombreTipoInstitucion;
    public TipoInstitucionResponseDto(TipoInstitucionEntity entity) {
        this.idTipoInstitucion = entity.getIdTipoInstitucion();
        this.nombreTipoInstitucion = entity.getNombreTipoInstitucion();
    }
}
