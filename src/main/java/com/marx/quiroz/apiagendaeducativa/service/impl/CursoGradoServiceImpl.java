package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.CursoGradoAddRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.CursoGradoAddResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.CursoEntity;
import com.marx.quiroz.apiagendaeducativa.entity.CursoGradoEntity;
import com.marx.quiroz.apiagendaeducativa.entity.GradoAcademicoEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.CursoGradoRepository;
import com.marx.quiroz.apiagendaeducativa.repository.GradoAcademicoRepository;
import com.marx.quiroz.apiagendaeducativa.repository.MsCursoRepository;
import com.marx.quiroz.apiagendaeducativa.service.CursoGradoService;
import org.springframework.stereotype.Service;

@Service
public class CursoGradoServiceImpl implements CursoGradoService {
    private final MsCursoRepository cursoRepository;
    private final GradoAcademicoRepository gradoRepository;
    private final CursoGradoRepository cursoGradoRepository;

    public CursoGradoServiceImpl(MsCursoRepository cursoRepository, GradoAcademicoRepository gradoRepository, CursoGradoRepository cursoGradoRepository) {
        this.cursoRepository = cursoRepository;
        this.gradoRepository = gradoRepository;
        this.cursoGradoRepository = cursoGradoRepository;
    }

    @Override
    public CursoGradoAddResponseDto agregarCursoAGrado(CursoGradoAddRequestDto dto) {
        // Validar grado
        GradoAcademicoEntity grado = gradoRepository.findById(dto.getIdGradoAcademico())
                .orElseThrow(() -> new InvalidOperationException("El grado académico no existe"));

        // Validar curso
        CursoEntity curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new InvalidOperationException("El curso no existe"));

        // Validar que ambos pertenecen a la misma institución
        if (!grado.getInstitucion().getIdInstitucion().equals(
                curso.getInstitucionEntity().getIdInstitucion()
        )) {
            throw new InvalidOperationException("El curso no pertenece a la misma institución del grado.");
        }

        // Validar duplicado
        if (cursoGradoRepository.existsByGradoAcademico_IdGradoAcademicoAndCurso_IdCurso(
                dto.getIdGradoAcademico(), dto.getIdCurso()
        )) {
            throw new InvalidOperationException("Este curso ya está asignado a este grado académico");
        }

        // Crear la relación
        CursoGradoEntity relacion = new CursoGradoEntity();
        relacion.setGradoAcademico(grado);
        relacion.setCurso(curso);

        cursoGradoRepository.save(relacion);

        return new CursoGradoAddResponseDto(
                grado.getIdGradoAcademico(),
                grado.getNombreGrado(),
                curso.getIdCurso(),
                curso.getNombreCurso()
        );
    }
}
