package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.PersonaInstitucionAddRequestDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaInstitucionAddResponseDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaResponseDTO;
import com.marx.quiroz.apiagendaeducativa.entity.PersonaEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonaService {
    PersonaEntity crearPersona(PersonaEntity persona);
    List<PersonaResponseDTO> obtenerPersonas();
    Page<PersonaResponseDTO> obtenerPersonasPaginado(int page, int size);

    List<PersonaResponseDTO> obtenerPersonasPorInstitucion(Integer idInstitucion);
    PersonaResponseDTO obtenerPersonaPorInstitucion(Integer idInstitucion, Integer idPersona);
    PersonaResponseDTO obtenerPersonaPorNumeroDocumento(String numeroDocumento);
    PersonaResponseDTO obtenerPersonaPorDocumentoEInstitucion(String numeroDocumento, Integer idInstitucion);

    PersonaInstitucionAddResponseDTO agregarPersonaAInstitucion(PersonaInstitucionAddRequestDTO dto);


}
