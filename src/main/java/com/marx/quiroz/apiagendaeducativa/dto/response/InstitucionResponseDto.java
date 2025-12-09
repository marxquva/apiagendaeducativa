package com.marx.quiroz.apiagendaeducativa.dto.response;

import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InstitucionResponseDto {
    private Integer idInstitucion;
    private String nombreInstitucion;
    private UUID codigo;
    private String descripcion;
    private String imagen;
    private String direccion;
    private String dominio;
    private String notaMinima;
    private String notaMaxima;
    private Integer estado;
    private TipoInstitucionResponseDto tipoInstitucion;

    public InstitucionResponseDto(InstitucionEntity entity) {
        this.idInstitucion = entity.getIdInstitucion();
        this.nombreInstitucion = entity.getNombreInstitucion();
        this.codigo = entity.getCodigo();
        this.estado = entity.getEstado();
        this.tipoInstitucion = new TipoInstitucionResponseDto(entity.getTipoInstitucion());
    }
}
