package com.marx.quiroz.apiagendaeducativa.dto.response;

import com.marx.quiroz.apiagendaeducativa.entity.CursoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CursoResponseDto {

    private Integer idCurso;
    private String nombreCurso;
    private String descripcion;
    private Integer estado;
    private Integer idInstitucion;
    private String nombreInstitucion;

    public CursoResponseDto(CursoEntity curso) {
        this.idCurso  = curso.getIdCurso();
        this.nombreCurso  = curso.getNombreCurso();
        this.descripcion  = curso.getDescripcion();
        this.estado = curso.getEstado();

        if (curso.getInstitucionEntity() != null) {
            this.idInstitucion = curso.getInstitucionEntity().getIdInstitucion();
            this.nombreInstitucion = curso.getInstitucionEntity().getNombreInstitucion();
        }
    }
}
