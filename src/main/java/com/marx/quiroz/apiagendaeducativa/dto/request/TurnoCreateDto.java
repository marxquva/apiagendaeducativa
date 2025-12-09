package com.marx.quiroz.apiagendaeducativa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TurnoCreateDto {

    @NotNull(message = "El nombre del turno es obligatorio")
    private String nombreTurno;
    private String descripcion;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFinal;

    @NotNull(message = "El ID de la institución es obligatorio")
    @Positive(message = "El ID de la institución debe de ser un numero positivo")
    private Integer idInstitucion;

    private Integer estado = 1;
}
