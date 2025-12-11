package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.GradoCursoProfesorCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.GradoCursoProfesorResponseDto;

import java.util.List;

public interface GradoCursoProfesorService {
    GradoCursoProfesorResponseDto asignarProfesor(GradoCursoProfesorCreateDto request);
    List<GradoCursoProfesorResponseDto> obtenerProfesoresPorGrado(Integer idGrado);
}
