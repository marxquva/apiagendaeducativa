package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.NivelCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.NivelResponseDto;

import java.util.List;

public interface MsNivelAcademicoService {
    List<NivelResponseDto> obtenerNiveles();
    NivelResponseDto obtenerNivelById(int id);
    NivelResponseDto obtenerNivelPorIdInstitucion(Integer idNivel, Integer idInstitucion);
    List<NivelResponseDto> obtenerNivelesPorInstitucion(Integer idInstitucion);
    NivelResponseDto crearNivel(NivelCreateDto nivelDto);
    NivelResponseDto actualizarNivel(Integer idNivel, NivelCreateDto nivelDto);
}
