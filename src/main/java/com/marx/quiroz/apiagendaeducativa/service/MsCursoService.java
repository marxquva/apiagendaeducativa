package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.CursoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.CursoResponseDto;

import java.util.List;

public interface MsCursoService {
    List<CursoResponseDto> obtenerCursos();
    CursoResponseDto obtenerCursoById(int id);
    CursoResponseDto obtenerCursoPorIdInstitucion(Integer idCurso, Integer idInstitucion);
    List<CursoResponseDto> obtenerCursosPorInstitucion(Integer idInstitucion);
    CursoResponseDto crearCurso(CursoCreateDto cursoDto);
    CursoResponseDto actualizarCurso(Integer idCurso, CursoCreateDto cursoDto);
}
