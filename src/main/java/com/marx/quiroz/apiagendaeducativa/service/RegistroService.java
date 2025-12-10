package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.RegistroRequestDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaResponseDTO;

public interface RegistroService {
    PersonaResponseDTO registrarPersonaUsuario(RegistroRequestDTO request);
}
