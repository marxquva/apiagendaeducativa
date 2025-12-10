package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.IndicadorCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.IndicadorResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.IndicadorEntity;
import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.exception.ResourceNotFoundException;
import com.marx.quiroz.apiagendaeducativa.repository.InstitucionRepository;

import com.marx.quiroz.apiagendaeducativa.repository.MsIndicadorRepository;
import com.marx.quiroz.apiagendaeducativa.service.MsIndicadorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsIndicadorServiceImpl implements MsIndicadorService {

    private final MsIndicadorRepository indicadorRepository;
    private final InstitucionRepository institucionRepository;
    public MsIndicadorServiceImpl(MsIndicadorRepository indicadorRepository, InstitucionRepository institucionRepository) {
        this.indicadorRepository = indicadorRepository;
        this.institucionRepository = institucionRepository;
    }

    @Override
    public List<IndicadorResponseDto> obtenerIndicadores() {
        return indicadorRepository.findAll()
                .stream()
                .map(IndicadorResponseDto::new)
                .toList();
    }

    @Override
    public IndicadorResponseDto obtenerIndicadorById(int id) {
        return null;
    }

    @Override
    public IndicadorResponseDto obtenerIndicadorPorIdInstitucion(Integer idIndicador, Integer idInstitucion) {
        IndicadorEntity indicador = indicadorRepository
                .findByIdIndicadorAndInstitucionEntity_IdInstitucion(idIndicador, idInstitucion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El indicador con ID " + idIndicador + " no pertenece a la institución " + idInstitucion
                ));

        return new IndicadorResponseDto(indicador);
    }

    @Override
    public List<IndicadorResponseDto> obtenerIndicadoresPorInstitucion(Integer idInstitucion) {
        List<IndicadorEntity> indicadores = indicadorRepository.findByInstitucionEntity_IdInstitucion(idInstitucion);

        if (indicadores.isEmpty()) {
            throw new ResourceNotFoundException("No existen indicadores para la institución con ID " + idInstitucion);
        }

        return indicadores.stream()
                .map(IndicadorResponseDto::new)
                .toList();
    }

    @Override
    public IndicadorResponseDto crearIndicador(IndicadorCreateDto indicadorDto) {
        Integer idInstitucion = indicadorDto.getIdInstitucion();
        String nombreIndicador = indicadorDto.getNombreIndicador();

        boolean existe = indicadorRepository
                .existsByNombreIndicadorAndInstitucionEntity_IdInstitucion(nombreIndicador, idInstitucion);

        if (existe) {
            throw new InvalidOperationException(
                    "El indicador '" + nombreIndicador + "' ya existe en esta institución."
            );
        }

        InstitucionEntity institucion = institucionRepository.findById(idInstitucion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La institución con ID " + idInstitucion + " no existe."
                ));

        IndicadorEntity indicador = new IndicadorEntity();
        indicador.setNombreIndicador(indicadorDto.getNombreIndicador());
        indicador.setDescripcion(indicadorDto.getDescripcion());
        indicador.setTipo(indicadorDto.getTipo());
        indicador.setEstado(indicadorDto.getEstado());
        indicador.setInstitucionEntity(institucion);

        indicadorRepository.save(indicador);
        return new IndicadorResponseDto(indicador);
    }

    @Override
    public IndicadorResponseDto actualizarIndicador(Integer idIndicador, IndicadorCreateDto indicadorDto) {
        IndicadorEntity indicadorExistente = indicadorRepository.findById(idIndicador)
                .orElseThrow(() -> new ResourceNotFoundException("El indicador con ID " + idIndicador + " no existe."));

        Integer idInstitucion = indicadorExistente.getInstitucionEntity().getIdInstitucion();
        String nuevoNombre = indicadorDto.getNombreIndicador();

        // VALIDACIÓN: comprobar si existe otro indicador con mismo nombre en la institución
        boolean existeDuplicado = indicadorRepository
                .existsByNombreIndicadorAndInstitucionEntity_IdInstitucionAndIdIndicadorNot(
                        nuevoNombre,
                        idInstitucion,
                        idIndicador
                );

        if (existeDuplicado) {
            throw new InvalidOperationException(
                    "El nombre de indicador '" + nuevoNombre + "' ya existe en esta institución."
            );
        }

        // Actualizar valores
        indicadorExistente.setNombreIndicador(indicadorDto.getNombreIndicador());
        indicadorExistente.setDescripcion(indicadorDto.getDescripcion());
        indicadorExistente.setEstado(indicadorDto.getEstado());

        IndicadorEntity actualizado = indicadorRepository.save(indicadorExistente);
        return new IndicadorResponseDto(actualizado);
    }
}
