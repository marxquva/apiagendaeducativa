package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.entity.TipoDocumentoEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.TipoDocumentoRepository;
import com.marx.quiroz.apiagendaeducativa.service.TipoDocumentoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;
    public TipoDocumentoServiceImpl(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public TipoDocumentoEntity crearTipoDocumento(TipoDocumentoEntity tipoDocumento) {

        if(tipoDocumentoRepository.existsByNombreTipoDocumento(tipoDocumento.getNombreTipoDocumento())){
            throw new InvalidOperationException("El nombre del tipo de documento " + tipoDocumento.getNombreTipoDocumento() + " ya está registrado");
        }

        TipoDocumentoEntity tipoDocumentoEntity = new TipoDocumentoEntity();
        tipoDocumentoEntity.setNombreTipoDocumento(tipoDocumento.getNombreTipoDocumento());
        tipoDocumentoEntity.setDescripcion(tipoDocumento.getDescripcion());
        tipoDocumentoEntity.setUtilidad(tipoDocumento.getUtilidad());
        tipoDocumentoEntity.setTipo(tipoDocumento.getTipo());
        tipoDocumentoEntity.setColorEtiqueta(tipoDocumento.getColorEtiqueta());
        tipoDocumentoEntity.setExpresionRegular(tipoDocumento.getExpresionRegular());
        tipoDocumentoRepository.save(tipoDocumentoEntity);
        return tipoDocumentoEntity;
    }

    @Override
    public List<TipoDocumentoEntity> obtenerTiposDocumento() {
        return tipoDocumentoRepository.findAll();
    }

    @Override
    public TipoDocumentoEntity obtenerTipoDocumentoById(int id) {
        return tipoDocumentoRepository.findById(id).
                orElseThrow(() -> new InvalidOperationException("Tipo de documento con ID " + id + " no se encuentra."));
    }

    @Override
    public TipoDocumentoEntity actualizarTipoDocumento(Integer id, TipoDocumentoEntity tipoDocumento) {

        TipoDocumentoEntity tipoDocumentoExistente = tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new InvalidOperationException("Tipo de documento con ID " + id + " no se encuentra."));

        String nombreTipoDocumento = tipoDocumento.getNombreTipoDocumento();
        if(tipoDocumentoRepository.existsByNombreTipoDocumento(nombreTipoDocumento)
                && !tipoDocumentoExistente.getNombreTipoDocumento().equals(nombreTipoDocumento)) {

            throw new InvalidOperationException("El nombre del tipo de documento " + nombreTipoDocumento + " ya está registrado");
        }

        tipoDocumentoExistente.setNombreTipoDocumento(tipoDocumento.getNombreTipoDocumento());
        tipoDocumentoExistente.setDescripcion(tipoDocumento.getDescripcion());
        tipoDocumentoExistente.setUtilidad(tipoDocumento.getUtilidad());
        tipoDocumentoExistente.setTipo(tipoDocumento.getTipo());
        tipoDocumentoExistente.setColorEtiqueta(tipoDocumento.getColorEtiqueta());
        tipoDocumentoExistente.setExpresionRegular(tipoDocumento.getExpresionRegular());
        tipoDocumentoExistente.setEstado(tipoDocumento.getEstado());
        return tipoDocumentoRepository.save(tipoDocumentoExistente);

    }
}
