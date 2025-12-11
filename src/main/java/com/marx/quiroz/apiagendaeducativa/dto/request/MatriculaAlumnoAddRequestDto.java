package com.marx.quiroz.apiagendaeducativa.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaAlumnoAddRequestDto {
    @NotNull(message = "El ID de la persona es obligatorio")
    @Positive(message = "El ID de la persona debe de ser un numero positivo")
    private Integer idPersona;
    @NotNull(message = "El ID del grado es obligatorio")
    @Positive(message = "El ID del grado debe de ser un numero positivo")
    private Integer idGradoAcademico;
    @NotNull(message = "El ID de la institución es obligatorio")
    @Positive(message = "El ID de la institución debe de ser un numero positivo")
    private Integer idInstitucion;
}
