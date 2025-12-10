package com.marx.quiroz.apiagendaeducativa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonaResponseDTO {
    private Integer idPersona;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String direccion;
    private String telefono;
    private String email;
    private String fechaNacimiento;
    private Integer idTipoDocumento;
    private String numeroDocumento;

    private UsuarioResponseDTO usuario;
    // Roles académicos asociados por institución
    private List<RolAcademicoResponseDTO> rolesAcademicos;

}
