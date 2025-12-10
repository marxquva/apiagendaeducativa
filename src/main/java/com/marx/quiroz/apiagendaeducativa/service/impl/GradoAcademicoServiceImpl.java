package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.GradoAcademicoCreateDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.GradoAcademicoResponseDTO;
import com.marx.quiroz.apiagendaeducativa.entity.*;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.*;
import com.marx.quiroz.apiagendaeducativa.service.GradoAcademicoService;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GradoAcademicoServiceImpl implements GradoAcademicoService {
    private final GradoAcademicoRepository gradoRepository;
    private final InstitucionRepository institucionRepository;
    private final PeriodoRepository periodoRepository;
    private final MsNivelAcademicoRepository nivelRepository;
    private final MsTurnoRepository turnoRepository;

    public GradoAcademicoServiceImpl(GradoAcademicoRepository gradoRepository, InstitucionRepository institucionRepository, PeriodoRepository periodoRepository, MsNivelAcademicoRepository nivelRepository, MsTurnoRepository turnoRepository) {
        this.gradoRepository = gradoRepository;
        this.institucionRepository = institucionRepository;
        this.periodoRepository = periodoRepository;
        this.nivelRepository = nivelRepository;
        this.turnoRepository = turnoRepository;
    }

    @Override
    public GradoAcademicoResponseDTO crearGrado(GradoAcademicoCreateDTO gradoDto) {

        PeriodoEntity periodo = periodoRepository.findById(gradoDto.getIdAnioAcademico())
                .orElseThrow(() -> new InvalidOperationException("Año académico no existe"));

        InstitucionEntity institucion = institucionRepository.findById(gradoDto.getIdInstitucion())
                .orElseThrow(() -> new InvalidOperationException("Institución no existe"));

        NivelEntity nivel = nivelRepository.findById(gradoDto.getIdNivelAcademico())
                .orElseThrow(() -> new InvalidOperationException("Nivel académico no existe"));

        TurnoEntity turno = turnoRepository.findById(gradoDto.getIdTurno())
                .orElseThrow(() -> new InvalidOperationException("Turno no existe"));

        boolean exists = gradoRepository
                .existsByNombreGradoAndAnioAcademico_IdAnioAcademicoAndInstitucion_IdInstitucionAndNivelAcademico_IdNivelAcademicoAndTurno_IdTurno(
                        gradoDto.getNombreGrado(),
                        gradoDto.getIdAnioAcademico(),
                        gradoDto.getIdInstitucion(),
                        gradoDto.getIdNivelAcademico(),
                        gradoDto.getIdTurno()
                );

        if (exists) {
            throw new InvalidOperationException("Ya existe un grado con el mismo nombre y combinación de periodo, institución, nivel y turno.");
        }

        GradoAcademicoEntity grado = new GradoAcademicoEntity();
        grado.setNombreGrado(gradoDto.getNombreGrado());
        grado.setDescripcionAula(gradoDto.getDescripcionAula());
        grado.setNumeroVacante(gradoDto.getNumeroVacante());
        grado.setEstado(1);
        grado.setAnioAcademico(periodo);
        grado.setInstitucion(institucion);
        grado.setNivelAcademico(nivel);
        grado.setTurno(turno);

        gradoRepository.save(grado);
        return mapToResponse(grado);
    }

    @Override
    public GradoAcademicoResponseDTO obtenerGrado(Integer id) {
        GradoAcademicoEntity grado = gradoRepository.findById(id)
                .orElseThrow(() -> new InvalidOperationException("Grado no existe"));
        return mapToResponse(grado);
    }

    @Override
    public List<GradoAcademicoResponseDTO> obtenerGradosPorInstitucion(Integer idInstitucion) {
        return gradoRepository.findAllByInstitucion_IdInstitucion(idInstitucion)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public GradoAcademicoResponseDTO actualizarGrado(Integer id, GradoAcademicoCreateDTO gradoDto) {

        GradoAcademicoEntity grado = gradoRepository.findById(id)
                .orElseThrow(() -> new InvalidOperationException("Grado no existe"));

        boolean exists = gradoRepository
                .existsByNombreGradoAndAnioAcademico_IdAnioAcademicoAndInstitucion_IdInstitucionAndNivelAcademico_IdNivelAcademicoAndTurno_IdTurnoAndIdGradoAcademicoNot(
                        gradoDto.getNombreGrado(),
                        gradoDto.getIdAnioAcademico(),
                        gradoDto.getIdInstitucion(),
                        gradoDto.getIdNivelAcademico(),
                        gradoDto.getIdTurno(),
                        id
                );

        if (exists) {
            throw new InvalidOperationException("Ya existe otro grado con el mismo nombre y combinación de periodo, institución, nivel y turno.");
        }

        grado.setNombreGrado(gradoDto.getNombreGrado());
        grado.setDescripcionAula(gradoDto.getDescripcionAula());
        grado.setNumeroVacante(gradoDto.getNumeroVacante());
        grado.setEstado(gradoDto.getEstado());
        grado.setFechaModificacion(LocalDateTime.now());

        return mapToResponse(gradoRepository.save(grado));
    }

    private GradoAcademicoResponseDTO mapToResponse(GradoAcademicoEntity g) {

        GradoAcademicoResponseDTO dto = new GradoAcademicoResponseDTO();

        dto.setIdGradoAcademico(g.getIdGradoAcademico());
        dto.setNombreGrado(g.getNombreGrado());
        dto.setDescripcionAula(g.getDescripcionAula());
        dto.setNumeroVacante(g.getNumeroVacante());
        dto.setEstado(g.getEstado());

        dto.setIdAnioAcademico(g.getAnioAcademico().getIdAnioAcademico());
        dto.setCodigo(g.getAnioAcademico().getCodigo());

        dto.setIdInstitucion(g.getInstitucion().getIdInstitucion());
        dto.setNombreInstitucion(g.getInstitucion().getNombreInstitucion());

        dto.setIdNivelAcademico(g.getNivelAcademico().getIdNivelAcademico());
        dto.setNombreNivel(g.getNivelAcademico().getNombreNivelAcademico());

        dto.setIdTurno(g.getTurno().getIdTurno());
        dto.setNombreTurno(g.getTurno().getNombreTurno());

        return dto;
    }

}
