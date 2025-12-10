package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.GradoAcademicoCreateDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.GradoAcademicoResponseDTO;

import java.util.List;

public interface GradoAcademicoService {
    GradoAcademicoResponseDTO crearGrado(GradoAcademicoCreateDTO dto);
    GradoAcademicoResponseDTO actualizarGrado(Integer id, GradoAcademicoCreateDTO dto);
    GradoAcademicoResponseDTO obtenerGrado(Integer id);
    List<GradoAcademicoResponseDTO> obtenerGradosPorInstitucion(Integer idInstitucion);
}
