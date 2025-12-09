package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.entity.RolEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TipoMensajeEntity;

import java.util.List;

public interface TipoMensajeService {
    TipoMensajeEntity crearTipoMensaje(TipoMensajeEntity tipoMensaje);
    List<TipoMensajeEntity> obtenerTiposMensaje();
    TipoMensajeEntity obtenerTipoMensajeById(int id);
    TipoMensajeEntity actualizarTipoMensaje(Integer id, TipoMensajeEntity tipoMensaje);
}
