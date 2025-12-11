package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.ApoderadoAlumnoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.ApoderadoAlumnoResponseDto;

import java.util.List;

public interface ApoderadoAlumnoService {
    ApoderadoAlumnoResponseDto asignarApoderado(ApoderadoAlumnoCreateDto request);
    List<ApoderadoAlumnoResponseDto> obtenerApoderadosPorAlumno(Integer idAlumno);
}
