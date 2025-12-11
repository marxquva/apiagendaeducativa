package com.marx.quiroz.apiagendaeducativa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class GradoCursoProfesorResponseDto {
    private Integer idCursoGrado;
    private String nombreAsignacion;
    private LocalDateTime fechaAsignacion;
    private Integer estado;

    private String grado;
    private String curso;
    private String profesor;
}
