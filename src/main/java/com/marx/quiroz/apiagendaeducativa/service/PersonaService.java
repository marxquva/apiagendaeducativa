package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.dto.request.PersonaInstitucionAddRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaInstitucionAddResponseDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.PersonaEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonaService {
    PersonaEntity crearPersona(PersonaEntity persona);
    List<PersonaResponseDto> obtenerPersonas();
    Page<PersonaResponseDto> obtenerPersonasPaginado(int page, int size);

    List<PersonaResponseDto> obtenerPersonasPorInstitucion(Integer idInstitucion);
    PersonaResponseDto obtenerPersonaPorInstitucion(Integer idInstitucion, Integer idPersona);
    PersonaResponseDto obtenerPersonaPorNumeroDocumento(String numeroDocumento);
    PersonaResponseDto obtenerPersonaPorDocumentoEInstitucion(String numeroDocumento, Integer idInstitucion);

    PersonaInstitucionAddResponseDto agregarPersonaAInstitucion(PersonaInstitucionAddRequestDto dto);


}
