package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.RegistroRequestDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.RegistroResponseDTO;

public interface RegistroService {
    RegistroResponseDTO registrarPersonaUsuario(RegistroRequestDTO request);
}
