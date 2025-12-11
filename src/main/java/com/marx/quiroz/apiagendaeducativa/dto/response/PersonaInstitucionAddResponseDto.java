package com.marx.quiroz.apiagendaeducativa.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonaInstitucionAddResponseDto {
    private Integer idPersona;
    private Integer idInstitucion;
    private Integer idRolAcademico;
    private String nombreRol;
}
