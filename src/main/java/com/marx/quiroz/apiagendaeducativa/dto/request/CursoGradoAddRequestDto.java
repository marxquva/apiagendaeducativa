package com.marx.quiroz.apiagendaeducativa.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoGradoAddRequestDto {
    @NotNull
    private Integer idGradoAcademico;

    @NotNull
    private Integer idCurso;
}
