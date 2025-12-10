package com.marx.quiroz.apiagendaeducativa.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NivelCreateDto {
    @NotNull(message = "El nombre del nivel academico es obligatorio")
    private String nombreNivelAcademico;
    private String descripcion;
    private Integer estado = 1;

    @NotNull(message = "El ID de la institución es obligatorio")
    @Positive(message = "El ID de la institución debe de ser un numero positivo")
    private Integer idInstitucion;


}
