package com.marx.quiroz.apiagendaeducativa.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaInstitucionAddRequestDTO {
    @NotNull(message = "El ID de persona es obligatorio")
    private Integer idPersona;

    @NotNull(message = "El ID de institución es obligatorio")
    private Integer idInstitucion;

    @NotNull(message = "El ID del rol académico es obligatorio")
    private Integer idRolAcademico;
}
