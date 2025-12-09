package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.entity.PersonaEntity;
import com.marx.quiroz.apiagendaeducativa.repository.PersonaRepository;
import com.marx.quiroz.apiagendaeducativa.service.PersonaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaEntity crearInstitucion(PersonaEntity persona) {
        return null;
    }

    @Override
    public List<PersonaEntity> obtenerPersonas() {
        return List.of();
    }

    @Override
    public PersonaEntity obtenerPersonaById(int id) {
        return null;
    }
}
