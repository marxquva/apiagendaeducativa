package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.MatriculaAlumnoAddRequestDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.MatriculaAlumnoAddResponseDTO;

public interface MatriculaAlumnoService {
    MatriculaAlumnoAddResponseDTO matricularAlumno(MatriculaAlumnoAddRequestDTO dto);
}
