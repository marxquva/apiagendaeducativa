package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.TurnoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.TurnoResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.TurnoEntity;

import java.util.List;

public interface MsTurnoService {
    List<TurnoResponseDto> obtenerTurnos();
    TurnoResponseDto  obtenerTurnoById(int id);
    TurnoResponseDto  obtenerTurnoPorIdInstitucion(Integer idTurno, Integer idInstitucion);
    List<TurnoResponseDto > obtenerTurnosPorInstitucion(Integer idInstitucion);
    TurnoResponseDto  crearTurno(TurnoCreateDto turno);
    TurnoResponseDto  actualizarTurno(Integer id, TurnoCreateDto turno);
}
