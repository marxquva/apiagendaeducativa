package com.marx.quiroz.apiagendaeducativa.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradoCursoProfesorCreateDto {
    private Integer idGradoAcademico;
    private Integer idCurso;
    private Integer idPersona;
    private String nombreAsignacion;
}
