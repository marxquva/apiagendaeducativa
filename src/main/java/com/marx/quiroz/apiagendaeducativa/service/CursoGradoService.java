package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.CursoGradoAddRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.CursoGradoAddResponseDto;

public interface CursoGradoService {
    CursoGradoAddResponseDto agregarCursoAGrado(CursoGradoAddRequestDto dto);
}
