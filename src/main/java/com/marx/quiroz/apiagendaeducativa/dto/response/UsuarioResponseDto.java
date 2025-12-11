package com.marx.quiroz.apiagendaeducativa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UsuarioResponseDto {
    private Integer idUsuario;
    private String username;
    // Perfiles de sistema asociados por institución
    private List<PerfilSistemaResponseDto> perfilesSistema;
}
