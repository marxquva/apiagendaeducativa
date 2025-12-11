package com.marx.quiroz.apiagendaeducativa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatriculaAlumnoAddResponseDto {
    private Integer idMatricula;
    private String codigo;
    private String fechaMatricula;

    private Integer idPersona;
    private String nombreCompleto;

    private Integer idGrado;
    private String nombreGrado;
}
