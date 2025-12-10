package com.marx.quiroz.apiagendaeducativa.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradoAcademicoCreateDTO {
    private String nombreGrado;
    private String descripcionAula;
    private Integer numeroVacante;
    private Integer estado = 1;

    private Integer idAnioAcademico;
    private Integer idInstitucion;
    private Integer idNivelAcademico;
    private Integer idTurno;
}
