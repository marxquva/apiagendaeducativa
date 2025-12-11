package com.marx.quiroz.apiagendaeducativa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApoderadoAlumnoResponseDto {
    private Integer idApoderadoAlumno;
    private String parentesco;
    private Integer esPrincipal;

    private String alumno;
    private String apoderado;
}
