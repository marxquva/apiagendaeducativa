package com.marx.quiroz.apiagendaeducativa.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicadorCreateDto {

    @NotNull(message = "El nombre del indicador es obligatorio")
    private String nombreIndicador;
    private String descripcion;
    private Integer tipo;
    private Integer estado = 1;

    @NotNull(message = "El ID de la institución es obligatorio")
    @Positive(message = "El ID de la institución debe de ser un numero positivo")
    private Integer idInstitucion;
}
