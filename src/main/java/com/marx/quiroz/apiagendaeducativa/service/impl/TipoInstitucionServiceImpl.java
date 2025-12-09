package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.entity.TipoInstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.TipoInstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.service.TipoInstitucionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TipoInstitucionServiceImpl implements TipoInstitucionService {

    private final TipoInstitucionRepository tipoInstitucionRepository;
    public TipoInstitucionServiceImpl(TipoInstitucionRepository tipoInstitucionRepository) {
        this.tipoInstitucionRepository = tipoInstitucionRepository;
    }

    @Override
    public TipoInstitucionEntity crearTipoInstitucion(TipoInstitucionEntity tipoInstitucion) {

        if(tipoInstitucionRepository.existsByNombreTipoInstitucion(tipoInstitucion.getNombreTipoInstitucion())){
            throw new InvalidOperationException("El nombre del tipo de institucion " + tipoInstitucion.getNombreTipoInstitucion() + " ya está registrado");
        }

        TipoInstitucionEntity tipoInstitucionEntity = new TipoInstitucionEntity();
        tipoInstitucionEntity.setNombreTipoInstitucion(tipoInstitucion.getNombreTipoInstitucion());
        tipoInstitucionEntity.setDescripcion(tipoInstitucion.getDescripcion());
        tipoInstitucionRepository.save(tipoInstitucionEntity);
        return tipoInstitucionEntity;
    }

    @Override
    public List<TipoInstitucionEntity> obtenerTiposInstitucion() {
        return tipoInstitucionRepository.findAll();
    }

    @Override
    public TipoInstitucionEntity obtenerTipoInstitucionById(int id) {
        return tipoInstitucionRepository.findById(id).
                orElseThrow(() -> new InvalidOperationException("Tipo de institucion con ID " + id + " no se encuentra."));
    }

    @Override
    public TipoInstitucionEntity actualizarTipoInstitucion(Integer id, TipoInstitucionEntity tipoInstitucion) {

        TipoInstitucionEntity tipoInstitucionExistente = tipoInstitucionRepository.findById(id)
                .orElseThrow(() -> new InvalidOperationException("Tipo de institucion con ID " + id + " no se encuentra."));

        if(tipoInstitucionRepository.existsByNombreTipoInstitucion(tipoInstitucion.getNombreTipoInstitucion())
                && !tipoInstitucionExistente.getNombreTipoInstitucion().equals(tipoInstitucion.getNombreTipoInstitucion())) {

            throw new InvalidOperationException("El nombre del tipo de institucion " + tipoInstitucion.getNombreTipoInstitucion() + " ya está registrado");
        }

        tipoInstitucionExistente.setNombreTipoInstitucion(tipoInstitucion.getNombreTipoInstitucion());
        tipoInstitucionExistente.setDescripcion(tipoInstitucion.getDescripcion());
        tipoInstitucionExistente.setFechaModificacion(LocalDateTime.now());
        tipoInstitucionExistente.setEstado(tipoInstitucion.getEstado());
        return tipoInstitucionRepository.save(tipoInstitucionExistente);

    }
}
