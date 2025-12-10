package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.CursoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.CursoResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.CursoEntity;
import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.exception.ResourceNotFoundException;
import com.marx.quiroz.apiagendaeducativa.repository.InstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.repository.MsCursoRepository;

import com.marx.quiroz.apiagendaeducativa.service.MsCursoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsCursoServiceImpl implements MsCursoService {

    private final MsCursoRepository cursoRepository;
    private final InstitucionRepository institucionRepository;
    public MsCursoServiceImpl(MsCursoRepository cursoRepository, InstitucionRepository institucionRepository) {
        this.cursoRepository = cursoRepository;
        this.institucionRepository = institucionRepository;
    }

    @Override
    public List<CursoResponseDto> obtenerCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(CursoResponseDto::new)
                .toList();
    }

    @Override
    public CursoResponseDto obtenerCursoById(int id) {
        return null;
    }

    @Override
    public CursoResponseDto obtenerCursoPorIdInstitucion(Integer idCurso, Integer idInstitucion) {
        CursoEntity curso = cursoRepository
                .findByIdCursoAndInstitucionEntity_IdInstitucion(idCurso, idInstitucion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El curso con ID " + idCurso + " no pertenece a la institución " + idInstitucion
                ));

        return new CursoResponseDto(curso);
    }

    @Override
    public List<CursoResponseDto> obtenerCursosPorInstitucion(Integer idInstitucion) {
        List<CursoEntity> cursos = cursoRepository.findByInstitucionEntity_IdInstitucion(idInstitucion);

        if (cursos.isEmpty()) {
            throw new ResourceNotFoundException("No existen cursos para la institución con ID " + idInstitucion);
        }

        return cursos.stream()
                .map(CursoResponseDto::new)
                .toList();
    }

    @Override
    public CursoResponseDto crearCurso(CursoCreateDto cursoDto) {

        Integer idInstitucion = cursoDto.getIdInstitucion();
        String nombreCurso = cursoDto.getNombreCurso();

        boolean existe = cursoRepository
                .existsByNombreCursoAndInstitucionEntity_IdInstitucion(nombreCurso, idInstitucion);

        if (existe) {
            throw new InvalidOperationException(
                    "El curso '" + nombreCurso + "' ya existe en esta institución."
            );
        }

        InstitucionEntity institucion = institucionRepository.findById(idInstitucion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La institución con ID " + idInstitucion + " no existe."
                ));

        CursoEntity curso = new CursoEntity();
        curso.setNombreCurso(cursoDto.getNombreCurso());
        curso.setDescripcion(cursoDto.getDescripcion());
        curso.setEstado(cursoDto.getEstado());
        curso.setInstitucionEntity(institucion);

        cursoRepository.save(curso);
        return new CursoResponseDto(curso);
    }

    @Override
    public CursoResponseDto actualizarCurso(Integer idCurso, CursoCreateDto cursoDto) {

        CursoEntity cursoExistente = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResourceNotFoundException("El curso con ID " + idCurso + " no existe."));

        Integer idInstitucion = cursoExistente.getInstitucionEntity().getIdInstitucion();
        String nuevoNombre = cursoDto.getNombreCurso();

        // VALIDACIÓN: comprobar si existe otro curso con mismo nombre en la institución
        boolean existeDuplicado = cursoRepository
                .existsByNombreCursoAndInstitucionEntity_IdInstitucionAndIdCursoNot(
                        nuevoNombre,
                        idInstitucion,
                        idCurso
                );

        if (existeDuplicado) {
            throw new InvalidOperationException(
                    "El nombre de curso '" + nuevoNombre + "' ya existe en esta institución."
            );
        }

        // Actualizar valores
        cursoExistente.setNombreCurso(cursoDto.getNombreCurso());
        cursoExistente.setDescripcion(cursoDto.getDescripcion());
        cursoExistente.setEstado(cursoDto.getEstado());

        CursoEntity actualizado = cursoRepository.save(cursoExistente);
        return new CursoResponseDto(actualizado);
    }
}
