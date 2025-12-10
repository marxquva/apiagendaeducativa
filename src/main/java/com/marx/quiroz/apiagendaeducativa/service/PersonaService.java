package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {
    PersonaEntity crearPersona(PersonaEntity persona);
    List<PersonaEntity> obtenerPersonas();
    PersonaEntity obtenerPersonaById(int id);
}
