package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.InstitucionCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.InstitucionResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TipoInstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.exception.ResourceNotFoundException;
import com.marx.quiroz.apiagendaeducativa.repository.InstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.repository.TipoInstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.service.InstitucionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstitucionImpl implements InstitucionService {

    private final InstitucionRepository institucionRepository;
    private final TipoInstitucionRepository tipoInstitucionRepository;
    public InstitucionImpl(InstitucionRepository institucionRepository, TipoInstitucionRepository tipoInstitucionRepository) {
        this.institucionRepository = institucionRepository;
        this.tipoInstitucionRepository = tipoInstitucionRepository;
    }

    @Override
    public List<InstitucionResponseDto> obtenerInstituciones() {
        //return institucionRepository.findAll();
        return institucionRepository.findAll()
                .stream()
                .map(InstitucionResponseDto::new)
                .toList();
    }

    @Override
    public List<InstitucionResponseDto> obtenerInstitucionesPorTipo(Integer idTipo) {
        List<InstitucionEntity> instituciones =
                institucionRepository.findByTipoInstitucion_IdTipoInstitucion(idTipo);

        if (instituciones.isEmpty()) {
            throw new ResourceNotFoundException("No hay instituciones para el tipo con ID " + idTipo);
        }

        return instituciones.stream()
                .map(InstitucionResponseDto::new)
                .toList();
    }

    @Override
    public InstitucionResponseDto obtenerInstitucionById(int id) {
        InstitucionEntity institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institucion con ID " + id + " Institucion"));
        return new InstitucionResponseDto(institucion);
    }

    @Override
    public InstitucionResponseDto crearInstitucion(InstitucionCreateDto institucionDto) {

        String codigo = institucionDto.getNombreInstitucion();
        int tipoInstitucionId = institucionDto.getTipoInstitucionId();
        if(institucionRepository.existsByNombreInstitucion(codigo)){
            throw new InvalidOperationException("La institucion educativa " + codigo + " ya está registrado");
        }

        TipoInstitucionEntity tipoInstitucion = tipoInstitucionRepository.findById(tipoInstitucionId)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de institucion con ID " + tipoInstitucionId + " no existe"));

        InstitucionEntity institucionEntity = new InstitucionEntity();
        institucionEntity.setNombreInstitucion(institucionDto.getNombreInstitucion());
        institucionEntity.setDescripcion(institucionDto.getDescripcion());
        institucionEntity.setImagen(institucionDto.getImagen());
        institucionEntity.setDireccion(institucionDto.getDireccion());
        institucionEntity.setDominio(institucionDto.getDominio());
        institucionEntity.setNotaMinima(institucionDto.getNotaMinima());
        institucionEntity.setNotaMaxima(institucionDto.getNotaMaxima());
        institucionEntity.setTipoInstitucion(tipoInstitucion);

        institucionRepository.save(institucionEntity);
        return new InstitucionResponseDto(institucionEntity);
    }

    @Override
    public InstitucionResponseDto actualizarInstitucion(Integer id, InstitucionCreateDto institucionDto) {

        String nombre = institucionDto.getNombreInstitucion();
        int tipoInstitucionId = institucionDto.getTipoInstitucionId();
        InstitucionEntity institucionExistente = institucionRepository.findById(id)
                .orElseThrow(() -> new InvalidOperationException("Institucion con ID " + id + " no se encuentra."));

        if(institucionRepository.existsByNombreInstitucion(nombre)
                && !institucionExistente.getNombreInstitucion().equals(nombre)) {

            throw new InvalidOperationException("El nombre de la institucion educativa " + nombre + " ya está registrado");
        }

        TipoInstitucionEntity tipoInstitucion = tipoInstitucionRepository.findById(tipoInstitucionId)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de institucion con ID " + tipoInstitucionId + " no existe"));

        institucionExistente.setNombreInstitucion(institucionDto.getNombreInstitucion());
        institucionExistente.setDescripcion(institucionDto.getDescripcion());
        institucionExistente.setImagen(institucionDto.getImagen());
        institucionExistente.setDireccion(institucionDto.getDireccion());
        institucionExistente.setDominio(institucionDto.getDominio());
        institucionExistente.setNotaMinima(institucionDto.getNotaMinima());
        institucionExistente.setNotaMaxima(institucionDto.getNotaMaxima());
        institucionExistente.setFechaModificacion(LocalDateTime.now());
        institucionExistente.setEstado(institucionDto.getEstado());
        institucionExistente.setTipoInstitucion(tipoInstitucion);

        institucionRepository.save(institucionExistente);
        return new InstitucionResponseDto(institucionExistente);
    }

    @Override
    public InstitucionResponseDto cambiarEstado(Integer id, Integer nuevoEstado) {

        // Validar que sea 0 o 1
        if (nuevoEstado == null || (nuevoEstado != 0 && nuevoEstado != 1)) {
            throw new InvalidOperationException("El estado debe ser 0 o 1.");
        }

        InstitucionEntity institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institución con ID " + id + " no existe."));

        institucion.setEstado(nuevoEstado);
        institucion.setFechaModificacion(LocalDateTime.now());

        institucionRepository.save(institucion);
        return new InstitucionResponseDto(institucion);
    }

}
