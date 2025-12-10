package com.marx.quiroz.apiagendaeducativa.dto.response;

import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.entity.PerfilEntity;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
public class PerfilSistemaResponseDTO {
    private Integer idPerfilSistema;
    private String nombre;
    private String descripcion;
    private Integer idInstitucion;

    public PerfilSistemaResponseDTO(PerfilEntity perfil, Integer idInstitucion) {
        if (perfil != null) {
            this.idPerfilSistema = perfil.getIdPerfilSistema();
            this.nombre = perfil.getNombre();
            this.descripcion = perfil.getDescripcion();
        }
        this.idInstitucion = idInstitucion;
    }
}
