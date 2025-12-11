package com.marx.quiroz.apiagendaeducativa.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApoderadoAlumnoCreateDto {
    private Integer idInstitucion;
    private Integer idAlumno;
    private Integer idApoderado;
    private String parentesco;
    private Integer esPrincipal;
}
