package com.marx.quiroz.apiagendaeducativa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradoAcademicoResponseDTO {
    private Integer idGradoAcademico;
    private String nombreGrado;
    private String descripcionAula;
    private Integer numeroVacante;
    private Integer estado;

    private Integer idAnioAcademico;
    private Integer codigo;

    private Integer idInstitucion;
    private String nombreInstitucion;

    private Integer idNivelAcademico;
    private String nombreNivel;

    private Integer idTurno;
    private String nombreTurno;
}
