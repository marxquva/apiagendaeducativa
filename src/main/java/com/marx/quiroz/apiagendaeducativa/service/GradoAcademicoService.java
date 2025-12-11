package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.GradoAcademicoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.GradoAcademicoResponseDto;

import java.util.List;

public interface GradoAcademicoService {
    GradoAcademicoResponseDto crearGrado(GradoAcademicoCreateDto dto);
    GradoAcademicoResponseDto actualizarGrado(Integer id, GradoAcademicoCreateDto dto);
    GradoAcademicoResponseDto obtenerGrado(Integer id);
    List<GradoAcademicoResponseDto> obtenerGradosPorInstitucion(Integer idInstitucion);
}
