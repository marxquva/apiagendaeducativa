package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.GradoCursoProfesorCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.GradoCursoProfesorResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.*;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.*;
import com.marx.quiroz.apiagendaeducativa.service.GradoCursoProfesorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GradoCursoProfesorServiceImpl implements GradoCursoProfesorService {

    private final GradoCursoProfesorRepository repository;
    private final GradoAcademicoRepository gradoRepository;
    private final MsCursoRepository cursoRepository;
    private final PersonaRepository personaRepository;
    private final PersonaInstitucionRepository personaInstitucionRepository;

    public GradoCursoProfesorServiceImpl(GradoCursoProfesorRepository repository, GradoAcademicoRepository gradoRepository, MsCursoRepository cursoRepository, PersonaRepository personaRepository, PersonaInstitucionRepository personaInstitucionRepository) {
        this.repository = repository;
        this.gradoRepository = gradoRepository;
        this.cursoRepository = cursoRepository;
        this.personaRepository = personaRepository;
        this.personaInstitucionRepository = personaInstitucionRepository;
    }

    @Override
    public GradoCursoProfesorResponseDto asignarProfesor(GradoCursoProfesorCreateDto request) {

        GradoAcademicoEntity grado = gradoRepository.findById(request.getIdGradoAcademico())
                .orElseThrow(() -> new InvalidOperationException("Grado académico no encontrado"));

        CursoEntity curso = cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new InvalidOperationException("Curso no encontrado"));

        PersonaEntity profesor = personaRepository.findById(request.getIdPersona())
                .orElseThrow(() -> new InvalidOperationException("Persona no encontrada"));

        Integer ROL_PROFESOR = 4;

        Integer idInstitucionGrado = grado.getInstitucion().getIdInstitucion();

        List<PersonaInstitucionEntity> rolesEnInstitucion =
                personaInstitucionRepository.findByPersona_IdPersonaAndInstitucion_IdInstitucion(
                        request.getIdPersona(),
                        idInstitucionGrado
                );

        if (rolesEnInstitucion.isEmpty()) {
            throw new InvalidOperationException(
                    "El profesor no pertenece a la institución."
            );
        }

        boolean esProfesorEnInstitucion = rolesEnInstitucion.stream()
                .anyMatch(r -> Objects.equals(r.getRolAcademico().getIdRolAcademico(), ROL_PROFESOR));

        if (!esProfesorEnInstitucion) {
            throw new InvalidOperationException(
                    "Persona no tiene el rol de PROFESOR en esta institución"
            );
        }


        GradoCursoProfesorEntity entity = new GradoCursoProfesorEntity();
        entity.setGradoAcademico(grado);
        entity.setCurso(curso);
        entity.setProfesor(profesor);
        entity.setNombreAsignacion(request.getNombreAsignacion());
        entity.setFechaAsignacion(LocalDateTime.now());

        repository.save(entity);

        return toResponse(entity);
    }

    @Override
    public List<GradoCursoProfesorResponseDto> obtenerProfesoresPorGrado(Integer idGrado) {
        return repository.findByGradoAcademico_IdGradoAcademico(idGrado)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private GradoCursoProfesorResponseDto toResponse(GradoCursoProfesorEntity entity) {
        GradoCursoProfesorResponseDto dto = new GradoCursoProfesorResponseDto();
        dto.setIdCursoGrado(entity.getIdCursoGrado());
        dto.setNombreAsignacion(entity.getNombreAsignacion());
        dto.setFechaAsignacion(entity.getFechaAsignacion());
        dto.setEstado(entity.getEstado());
        dto.setGrado(entity.getGradoAcademico().getNombreGrado());
        dto.setCurso(entity.getCurso().getNombreCurso());
        dto.setProfesor(entity.getProfesor().getNombre() + " " + entity.getProfesor().getApellido());
        return dto;
    }
}
