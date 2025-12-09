package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.entity.TipoDocumentoEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TipoInstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TipoMensajeEntity;

import java.util.List;

public interface TipoDocumentoService {
    TipoDocumentoEntity crearTipoDocumento(TipoDocumentoEntity tipoDocumento);
    List<TipoDocumentoEntity> obtenerTiposDocumento();
    TipoDocumentoEntity obtenerTipoDocumentoById(int id);
    TipoDocumentoEntity actualizarTipoDocumento(Integer id, TipoDocumentoEntity tipoDocumento);
}
