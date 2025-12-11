package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.ApoderadoAlumnoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.ApoderadoAlumnoResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.ApoderadoAlumnoEntity;
import com.marx.quiroz.apiagendaeducativa.entity.PersonaEntity;
import com.marx.quiroz.apiagendaeducativa.entity.PersonaInstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.ApoderadoAlumnoRepository;
import com.marx.quiroz.apiagendaeducativa.repository.PersonaInstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.repository.PersonaRepository;
import com.marx.quiroz.apiagendaeducativa.service.ApoderadoAlumnoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApoderadoAlumnoServiceImpl implements ApoderadoAlumnoService {
    private final ApoderadoAlumnoRepository repository;
    private final PersonaRepository personaRepository;
    private final PersonaInstitucionRepository personaInstitucionRepository;

    public ApoderadoAlumnoServiceImpl(ApoderadoAlumnoRepository repository, PersonaRepository personaRepository,PersonaInstitucionRepository personaInstitucionRepository) {
        this.repository = repository;
        this.personaRepository = personaRepository;
        this.personaInstitucionRepository = personaInstitucionRepository;
    }

    @Override
    public ApoderadoAlumnoResponseDto asignarApoderado(ApoderadoAlumnoCreateDto request) {

        PersonaEntity alumno = personaRepository.findById(request.getIdAlumno())
                .orElseThrow(() -> new InvalidOperationException("Alumno no encontrado"));

        PersonaEntity apoderado = personaRepository.findById(request.getIdApoderado())
                .orElseThrow(() -> new InvalidOperationException("Apoderado no encontrado"));

        Integer idInstitucion = request.getIdInstitucion();
        final int ROL_ALUMNO = 5;
        final int ROL_POSTULANTE = 7;

        // ======================= VALIDACIÓN DE INSTITUCIÓN =======================

        List<PersonaInstitucionEntity> alumnoInstituciones =
                personaInstitucionRepository.findByPersona_IdPersonaAndInstitucion_IdInstitucion(
                        request.getIdAlumno(), idInstitucion
                );

        if (alumnoInstituciones.isEmpty()) {
            throw new InvalidOperationException(
                    "El alumno no pertenece a la institución especificada"
            );
        }

        List<PersonaInstitucionEntity> apoderadoInstituciones =
                personaInstitucionRepository.findByPersona_IdPersonaAndInstitucion_IdInstitucion(
                        request.getIdApoderado(), idInstitucion
                );

        if (apoderadoInstituciones.isEmpty()) {
            throw new InvalidOperationException(
                    "El apoderado no pertenece a la institución especificada"
            );
        }

        // ======================= VALIDACIÓN DE ROLES ============================

        // Alumno debe tener rol ALUMNO (5)
        boolean rolCorrectoAlumno = alumnoInstituciones.stream()
                .anyMatch(pi -> pi.getRolAcademico().getIdRolAcademico() == ROL_ALUMNO);

        if (!rolCorrectoAlumno) {
            throw new InvalidOperationException(
                    "La persona seleccionada como alumno NO tiene el rol de ALUMNO (5)"
            );
        }

        // Apoderado NO debe tener rol ALUMNO (5) ni POSTULANTE (7)
        boolean rolIncorrectoApoderado = apoderadoInstituciones.stream()
                .anyMatch(pi -> {
                    int rolId = pi.getRolAcademico().getIdRolAcademico();
                    return rolId == ROL_ALUMNO || rolId == ROL_POSTULANTE;
                });

        if (rolIncorrectoApoderado) {
            throw new InvalidOperationException(
                    "El apoderado no puede tener rol de ALUMNO (5) ni POSTULANTE (7)"
            );
        }

        // ======================= VALIDAR APODERADO PRINCIPAL =====================

        if (request.getEsPrincipal() == 1) {
            boolean yaExistePrincipal = repository.existsByAlumno_IdPersonaAndEsPrincipal(
                    request.getIdAlumno(), 1
            );

            if (yaExistePrincipal) {
                throw new InvalidOperationException(
                        "El alumno ya tiene un apoderado principal asignado"
                );
            }
        }

        // ======================= CREAR RELACIÓN =======================

        ApoderadoAlumnoEntity entity = new ApoderadoAlumnoEntity();
        entity.setAlumno(alumno);
        entity.setApoderado(apoderado);
        entity.setParentesco(request.getParentesco());
        entity.setEsPrincipal(request.getEsPrincipal());

        repository.save(entity);

        return toResponse(entity);
    }


    @Override
    public List<ApoderadoAlumnoResponseDto> obtenerApoderadosPorAlumno(Integer idAlumno) {
        return repository.findByAlumno_IdPersona(idAlumno)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ApoderadoAlumnoResponseDto toResponse(ApoderadoAlumnoEntity entity) {
        ApoderadoAlumnoResponseDto dto = new ApoderadoAlumnoResponseDto();

        dto.setIdApoderadoAlumno(entity.getIdApoderadoAlumno());
        dto.setParentesco(entity.getParentesco());
        dto.setEsPrincipal(entity.getEsPrincipal());

        dto.setAlumno(entity.getAlumno().getNombre() + " " + entity.getAlumno().getApellido());
        dto.setApoderado(entity.getApoderado().getNombre() + " " + entity.getApoderado().getApellido());

        return dto;
    }
}
