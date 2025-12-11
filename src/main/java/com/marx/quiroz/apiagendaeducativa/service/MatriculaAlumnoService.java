package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.MatriculaAlumnoAddRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.MatriculaAlumnoAddResponseDto;

public interface MatriculaAlumnoService {
    MatriculaAlumnoAddResponseDto matricularAlumno(MatriculaAlumnoAddRequestDto dto);
}
