package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.InstitucionCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.InstitucionResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;

import java.util.List;

public interface InstitucionService {
    InstitucionResponseDto crearInstitucion(InstitucionCreateDto institucionDto);
    List<InstitucionResponseDto> obtenerInstituciones();
    InstitucionResponseDto obtenerInstitucionById(int id);
    InstitucionResponseDto actualizarInstitucion(Integer id, InstitucionCreateDto institucionDto);
    List<InstitucionResponseDto> obtenerInstitucionesPorTipo(Integer idTipo);
    InstitucionResponseDto cambiarEstado(Integer id, Integer nuevoEstado);


}
