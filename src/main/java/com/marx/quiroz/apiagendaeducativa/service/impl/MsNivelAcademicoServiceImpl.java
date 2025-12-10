package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.NivelCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.NivelResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.entity.NivelEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.exception.ResourceNotFoundException;
import com.marx.quiroz.apiagendaeducativa.repository.InstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.repository.MsNivelAcademicoRepository;
import com.marx.quiroz.apiagendaeducativa.service.MsNivelAcademicoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsNivelAcademicoServiceImpl implements MsNivelAcademicoService {

    private final MsNivelAcademicoRepository nivelRepository;
    private final InstitucionRepository institucionRepository;

    public MsNivelAcademicoServiceImpl(MsNivelAcademicoRepository nivelRepository, InstitucionRepository institucionRepository) {
        this.nivelRepository = nivelRepository;
        this.institucionRepository = institucionRepository;
    }

    @Override
    public List<NivelResponseDto> obtenerNiveles() {
        return nivelRepository.findAll()
                .stream()
                .map(NivelResponseDto::new)
                .toList();
    }

    @Override
    public NivelResponseDto obtenerNivelById(int id) {
        return null;
    }

    @Override
    public NivelResponseDto obtenerNivelPorIdInstitucion(Integer idNivelAcademico, Integer idInstitucion) {
        NivelEntity nivel = nivelRepository
                .findByIdNivelAcademicoAndInstitucionEntity_IdInstitucion(idNivelAcademico, idInstitucion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El turno con ID " + idNivelAcademico + " no pertenece a la institución " + idInstitucion
                ));

        return new NivelResponseDto(nivel);
    }

    @Override
    public List<NivelResponseDto> obtenerNivelesPorInstitucion(Integer idInstitucion) {
        List<NivelEntity> niveles = nivelRepository.findByInstitucionEntity_IdInstitucion(idInstitucion);

        if (niveles.isEmpty()) {
            throw new ResourceNotFoundException("No existen niveles para la institución con ID " + idInstitucion);
        }

        return niveles.stream()
                .map(NivelResponseDto::new)
                .toList();
    }

    @Override
    public NivelResponseDto crearNivel(NivelCreateDto nivelDto) {

        Integer idInstitucion = nivelDto.getIdInstitucion();
        String nombreNivelAcademico = nivelDto.getNombreNivelAcademico();

        boolean existe = nivelRepository
                .existsByNombreNivelAcademicoAndInstitucionEntity_IdInstitucion(nombreNivelAcademico, idInstitucion);

        if (existe) {
            throw new InvalidOperationException(
                    "El nivel academico '" + nombreNivelAcademico + "' ya existe en esta institución."
            );
        }

        InstitucionEntity institucion = institucionRepository.findById(idInstitucion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La institución con ID " + idInstitucion + " no existe."
                ));

        NivelEntity nivel = new NivelEntity();
        nivel.setNombreNivelAcademico(nivelDto.getNombreNivelAcademico());
        nivel.setDescripcion(nivelDto.getDescripcion());
        nivel.setEstado(nivelDto.getEstado());
        nivel.setInstitucionEntity(institucion);

        nivelRepository.save(nivel);
        return new NivelResponseDto(nivel);

    }

    @Override
    public NivelResponseDto actualizarNivel(Integer idNivel, NivelCreateDto nivelDto) {

        NivelEntity nivelExistente = nivelRepository.findById(idNivel)
                .orElseThrow(() -> new ResourceNotFoundException("El nivel academico con ID " + idNivel + " no existe."));

        Integer idInstitucion = nivelExistente.getInstitucionEntity().getIdInstitucion();
        String nuevoNombre = nivelDto.getNombreNivelAcademico();

        // VALIDACIÓN: comprobar si existe otro nivel con mismo nombre en la institución
        boolean existeDuplicado = nivelRepository
                .existsByNombreNivelAcademicoAndInstitucionEntity_IdInstitucionAndIdNivelAcademicoNot(
                        nuevoNombre,
                        idInstitucion,
                        idNivel
                );

        if (existeDuplicado) {
            throw new InvalidOperationException(
                    "El nombre del nivel academico '" + nuevoNombre + "' ya existe en esta institución."
            );
        }

        // Actualizar valores
        nivelExistente.setNombreNivelAcademico(nivelDto.getNombreNivelAcademico());
        nivelExistente.setDescripcion(nivelDto.getDescripcion());
        nivelExistente.setEstado(nivelDto.getEstado());

        NivelEntity actualizado = nivelRepository.save(nivelExistente);
        return new NivelResponseDto(actualizado);

    }
}
