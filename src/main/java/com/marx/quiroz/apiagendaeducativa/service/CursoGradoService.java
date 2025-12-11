package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.CursoGradoAddRequestDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.CursoGradoAddResponseDTO;

public interface CursoGradoService {
    CursoGradoAddResponseDTO agregarCursoAGrado(CursoGradoAddRequestDTO dto);
}
