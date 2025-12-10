package com.marx.quiroz.apiagendaeducativa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UsuarioResponseDTO {
    private Integer idUsuario;
    private String username;
    // Perfiles de sistema asociados por institución
    private List<PerfilSistemaResponseDTO> perfilesSistema;
}
