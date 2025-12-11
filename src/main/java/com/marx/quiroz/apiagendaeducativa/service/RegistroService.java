package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.RegistroRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaResponseDto;

public interface RegistroService {
    PersonaResponseDto registrarPersonaUsuario(RegistroRequestDto request);
}
