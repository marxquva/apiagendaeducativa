package com.marx.quiroz.apiagendaeducativa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CursoGradoAddResponseDTO {
    private Integer idGradoAcademico;
    private String nombreGradoAcademico;
    private Integer idCurso;
    private String nombreCurso;
}
