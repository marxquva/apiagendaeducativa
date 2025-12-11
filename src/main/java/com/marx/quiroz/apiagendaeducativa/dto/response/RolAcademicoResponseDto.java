package com.marx.quiroz.apiagendaeducativa.dto.response;

import com.marx.quiroz.apiagendaeducativa.entity.RolEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolAcademicoResponseDto {
    private Integer idRolAcademico;
    private String nombre;
    private String descripcion;
    private Integer idInstitucion;
    public RolAcademicoResponseDto(RolEntity rol, Integer idInstitucion) {
        if (rol != null) {
            this.idRolAcademico = rol.getIdRolAcademico();
            this.nombre = rol.getNombre();
            this.descripcion = rol.getDescripcion();
        }
        this.idInstitucion = idInstitucion;
    }
}
