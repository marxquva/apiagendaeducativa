package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.MatriculaAlumnoAddRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.MatriculaAlumnoAddResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.GradoAcademicoEntity;
import com.marx.quiroz.apiagendaeducativa.entity.MatriculaAlumnoEntity;
import com.marx.quiroz.apiagendaeducativa.entity.PersonaEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.GradoAcademicoRepository;
import com.marx.quiroz.apiagendaeducativa.repository.MatriculaAlumnoRepository;
import com.marx.quiroz.apiagendaeducativa.repository.PersonaInstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.repository.PersonaRepository;
import com.marx.quiroz.apiagendaeducativa.service.MatriculaAlumnoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MatriculaAlumnoServiceImpl implements MatriculaAlumnoService {

    private final MatriculaAlumnoRepository matriculaRepository;
    private final PersonaRepository personaRepository;
    private final GradoAcademicoRepository gradoRepository;
    private final PersonaInstitucionRepository personaInstitucionRepository;

    public MatriculaAlumnoServiceImpl(MatriculaAlumnoRepository matriculaRepository, PersonaRepository personaRepository, GradoAcademicoRepository gradoRepository, PersonaInstitucionRepository personaInstitucionRepository) {
        this.matriculaRepository = matriculaRepository;
        this.personaRepository = personaRepository;
        this.gradoRepository = gradoRepository;
        this.personaInstitucionRepository = personaInstitucionRepository;
    }

    @Override
    public MatriculaAlumnoAddResponseDto matricularAlumno(MatriculaAlumnoAddRequestDto dto) {

        // 1. Validar persona
        PersonaEntity persona = personaRepository.findById(dto.getIdPersona())
                .orElseThrow(() -> new InvalidOperationException("La persona no existe"));

        // 2. Validar grado académico
        GradoAcademicoEntity grado = gradoRepository.findById(dto.getIdGradoAcademico())
                .orElseThrow(() -> new InvalidOperationException("El grado académico no existe"));

        // 3. Validar que el grado pertenece a la institución enviada
        if (!grado.getInstitucion().getIdInstitucion().equals(dto.getIdInstitucion())) {
            throw new InvalidOperationException(
                    "El grado académico no pertenece a la institución indicada");
        }

        Integer ROL_ALUMNO = 5;

        // 4. Validar que persona pertenece a la institución con rol 5
        boolean pertenece = personaInstitucionRepository
                .existsByPersonaIdPersonaAndInstitucionIdInstitucionAndRolAcademicoIdRolAcademico(
                        dto.getIdPersona(),
                        dto.getIdInstitucion(),
                        ROL_ALUMNO
                );

        if (!pertenece) {
            throw new InvalidOperationException(
                    "La persona no pertenece a la institución seleccionada con el rol ALUMNO (rol 5)"
            );
        }

        // 5. Validar que no está matriculado previamente en el mismo grado
        boolean existe = matriculaRepository
                .existsByPersona_IdPersonaAndGradoAcademico_IdGradoAcademico(
                        dto.getIdPersona(),
                        dto.getIdGradoAcademico()
                );

        if (existe) {
            throw new InvalidOperationException("El alumno ya está matriculado en este grado");
        }

        // 6. Crear matrícula
        MatriculaAlumnoEntity matricula = new MatriculaAlumnoEntity();
        matricula.setCodigo(UUID.randomUUID());
        matricula.setFechaMatricula(LocalDateTime.now());
        matricula.setPersona(persona);
        matricula.setGradoAcademico(grado);

        matricula = matriculaRepository.save(matricula);

        // 7. Respuesta
        return new MatriculaAlumnoAddResponseDto(
                matricula.getIdMatriculaAlumno(),
                matricula.getCodigo().toString(),
                matricula.getFechaMatricula().toString(),
                persona.getIdPersona(),
                persona.getNombre() + " " + persona.getApellido(),
                grado.getIdGradoAcademico(),
                grado.getNombreGrado()
        );
    }

}
