package com.marx.quiroz.apiagendaeducativa.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitucionCreateDto {

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 120, message = "El nombre debe de tener entre 3 y 120 caracteres")
    private String nombreInstitucion;

    private String descripcion;
    private String imagen;
    private String direccion;
    private String dominio;

    private String notaMinima;
    private String notaMaxima;

    @Positive(message = "El ID del tipo de institucion debe de ser un numero positivo")
    private int tipoInstitucionId;

    @Min(0)
    @Max(1)
    private Integer estado = 1;

}
