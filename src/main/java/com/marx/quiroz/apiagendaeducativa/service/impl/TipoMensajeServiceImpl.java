package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.entity.TipoMensajeEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.TipoMensajeRepository;
import com.marx.quiroz.apiagendaeducativa.service.TipoMensajeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMensajeServiceImpl implements TipoMensajeService {

    private final TipoMensajeRepository tipoMensajeRepository;
    public TipoMensajeServiceImpl(TipoMensajeRepository tipoMensajeRepository) {
        this.tipoMensajeRepository = tipoMensajeRepository;
    }

    @Override
    public TipoMensajeEntity crearTipoMensaje(TipoMensajeEntity tipoMensaje) {

        if(tipoMensajeRepository.existsByNombreTipoMensaje(tipoMensaje.getNombreTipoMensaje())){
            throw new InvalidOperationException("El nombre del tipo de mensaje " + tipoMensaje.getNombreTipoMensaje() + " ya está registrado");
        }

        TipoMensajeEntity tipoMensajeEntity = new TipoMensajeEntity();
        tipoMensajeEntity.setNombreTipoMensaje(tipoMensaje.getNombreTipoMensaje());
        tipoMensajeEntity.setDescripcion(tipoMensaje.getDescripcion());
        tipoMensajeEntity.setColorRgb((tipoMensaje.getColorRgb()));
        tipoMensajeEntity.setRequiereRespuesta(tipoMensaje.getRequiereRespuesta());
        tipoMensajeRepository.save(tipoMensajeEntity);
        return tipoMensajeEntity;
    }

    @Override
    public List<TipoMensajeEntity> obtenerTiposMensaje() {
        return tipoMensajeRepository.findAll();
    }

    @Override
    public TipoMensajeEntity obtenerTipoMensajeById(int id) {
        return tipoMensajeRepository.findById(id).
                orElseThrow(() -> new InvalidOperationException("Tipo de mensaje con ID " + id + " no se encuentra."));
    }

    @Override
    public TipoMensajeEntity actualizarTipoMensaje(Integer id, TipoMensajeEntity tipoMensaje) {

        TipoMensajeEntity tipoMensajeExistente = tipoMensajeRepository.findById(id)
                .orElseThrow(() -> new InvalidOperationException("Perfil con ID " + id + " no se encuentra."));

        if(tipoMensajeRepository.existsByNombreTipoMensaje(tipoMensaje.getNombreTipoMensaje())
                && !tipoMensajeExistente.getNombreTipoMensaje().equals(tipoMensaje.getNombreTipoMensaje())) {

            throw new InvalidOperationException("El nombre del tipo de mensaje " + tipoMensaje.getNombreTipoMensaje() + " ya está registrado");
        }

        tipoMensajeExistente.setNombreTipoMensaje(tipoMensaje.getNombreTipoMensaje());
        tipoMensajeExistente.setDescripcion(tipoMensaje.getDescripcion());
        tipoMensajeExistente.setColorRgb(tipoMensaje.getColorRgb());
        tipoMensajeExistente.setRequiereRespuesta(tipoMensaje.getRequiereRespuesta());
        tipoMensajeExistente.setEstado(tipoMensaje.getEstado());

        return tipoMensajeRepository.save(tipoMensajeExistente);
    }
}
