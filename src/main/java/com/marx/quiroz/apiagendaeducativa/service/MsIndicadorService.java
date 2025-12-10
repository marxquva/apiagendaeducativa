package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.IndicadorCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.IndicadorResponseDto;

import java.util.List;

public interface MsIndicadorService {
    List<IndicadorResponseDto> obtenerIndicadores();
    IndicadorResponseDto obtenerIndicadorById(int id);
    IndicadorResponseDto obtenerIndicadorPorIdInstitucion(Integer idIndicador, Integer idInstitucion);
    List<IndicadorResponseDto> obtenerIndicadoresPorInstitucion(Integer idInstitucion);
    IndicadorResponseDto crearIndicador(IndicadorCreateDto indicadorDto);
    IndicadorResponseDto actualizarIndicador(Integer idIndicador, IndicadorCreateDto indicadorDto);
}
